package tv.codely.shared.infrastructure.bus.event.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.context.ApplicationContext;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.Utils;
import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.infrastructure.bus.event.DomainEventJsonDeserializer;
import tv.codely.shared.infrastructure.bus.event.DomainEventSubscribersInformation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

@Service
public final class RabbitMqDomainEventsConsumer {
    private final String                            CONSUMER_NAME          = "domain_events_consumer";
    private final DomainEventSubscribersInformation information;
    private final DomainEventJsonDeserializer       deserializer;
    private final ApplicationContext context;
    private final HashMap<String, Object>           domainEventSubscribers = new HashMap<>();
    RabbitListenerEndpointRegistry registry;

    public RabbitMqDomainEventsConsumer(
        RabbitListenerEndpointRegistry registry,
        DomainEventSubscribersInformation information,
        DomainEventJsonDeserializer deserializer,
        ApplicationContext context
    ) {
        this.registry     = registry;
        this.information  = information;
        this.deserializer = deserializer;
        this.context      = context;
    }

    public void consume() {
        AbstractMessageListenerContainer container = (AbstractMessageListenerContainer) registry.getListenerContainer(
            CONSUMER_NAME
        );

        container.addQueueNames(information.rabbitMqFormattedNames());

        container.start();
    }

    @RabbitListener(id = CONSUMER_NAME, autoStartup = "false")
    public void consumer(Message message) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        String serializedMessage = new String(message.getBody());
        DomainEvent<?> domainEvent= deserializer.deserialize(serializedMessage);

        String queue             = message.getMessageProperties().getConsumerQueue();

        System.out.println(serializedMessage);
        System.out.println(message.getMessageProperties().getConsumerQueue());

        Object subscriber = domainEventSubscribers.containsKey(queue)
            ? domainEventSubscribers.get(queue)
            : subscriberFor(queue);

        System.out.println("VAAAAAAA");
        System.out.println("VAAAAAAA");
        System.out.println("VAAAAAAA");
        System.out.println("VAAAAAAA");
        System.out.println("VAAAAAAA");
        Method sumInstanceMethod = subscriber.getClass().getMethod("on", domainEvent.getClass());

        try {
            sumInstanceMethod.invoke(subscriber, domainEvent);
        } catch (Exception error) {
            System.out.println("IMPROVE THIS");
        }

        System.out.println("asd");
        System.out.println();
    }

    private Object subscriberFor(String queue) {
        String[] queueParts     = queue.split("\\.");
        String   subscriberName = Utils.toCamelFirstLower(queueParts[queueParts.length - 1]);

        try {
            Object subscriber = context.getBean(subscriberName);
            domainEventSubscribers.put(queue, subscriber);

            return subscriber;
        } catch (Exception e) {
            System.out.println(String.join("\n", context.getBeanDefinitionNames()));
            System.out.println(e.getMessage());
        }

        return null;
    }
}
