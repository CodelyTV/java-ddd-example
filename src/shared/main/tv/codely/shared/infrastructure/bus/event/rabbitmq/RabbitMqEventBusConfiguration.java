package tv.codely.shared.infrastructure.bus.event.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tv.codely.shared.infrastructure.bus.event.DomainEventSubscribersInformation;
import tv.codely.shared.infrastructure.bus.event.DomainEventsInformation;
import tv.codely.shared.infrastructure.config.Parameter;
import tv.codely.shared.infrastructure.config.ParameterNotExist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class RabbitMqEventBusConfiguration {
    private final DomainEventSubscribersInformation domainEventSubscribersInformation;
    private final DomainEventsInformation           domainEventsInformation;
    private final Parameter                         config;
    private final String                            exchangeName;

    public RabbitMqEventBusConfiguration(
        DomainEventSubscribersInformation domainEventSubscribersInformation,
        DomainEventsInformation domainEventsInformation,
        Parameter config
    ) throws ParameterNotExist {
        this.domainEventSubscribersInformation = domainEventSubscribersInformation;
        this.domainEventsInformation           = domainEventsInformation;
        this.config                            = config;
        this.exchangeName                      = config.get("RABBITMQ_EXCHANGE");
    }

    @Bean
    public CachingConnectionFactory connection() throws ParameterNotExist {
        CachingConnectionFactory factory = new CachingConnectionFactory();

        factory.setHost(config.get("RABBITMQ_HOST"));
        factory.setPort(config.getInt("RABBITMQ_PORT"));
        factory.setUsername(config.get("RABBITMQ_LOGIN"));
        factory.setPassword(config.get("RABBITMQ_PASSWORD"));

        return factory;
    }

    @Bean
    public Declarables declaration() {
        String retryExchangeName      = RabbitMqExchangeNameFormatter.retry(exchangeName);
        String deadLetterExchangeName = RabbitMqExchangeNameFormatter.deadLetter(exchangeName);

        TopicExchange    domainEventsExchange           = new TopicExchange(exchangeName, true, false);
        TopicExchange    retryDomainEventsExchange      = new TopicExchange(retryExchangeName, true, false);
        TopicExchange    deadLetterDomainEventsExchange = new TopicExchange(deadLetterExchangeName, true, false);
        List<Declarable> declarables                    = new ArrayList<>();
        declarables.add(domainEventsExchange);
        declarables.add(retryDomainEventsExchange);
        declarables.add(deadLetterDomainEventsExchange);

        Collection<Declarable> queuesAndBindings = declareQueuesAndBindings(
            domainEventsExchange,
            retryDomainEventsExchange,
            deadLetterDomainEventsExchange
        ).stream().flatMap(Collection::stream).collect(Collectors.toList());

        declarables.addAll(queuesAndBindings);

        return new Declarables(declarables);
    }

    private Collection<Collection<Declarable>> declareQueuesAndBindings(
        TopicExchange domainEventsExchange,
        TopicExchange retryDomainEventsExchange,
        TopicExchange deadLetterDomainEventsExchange
    ) {
        return domainEventSubscribersInformation.all().stream().map(information -> {
            String queueName           = RabbitMqQueueNameFormatter.format(information);
            String retryQueueName      = RabbitMqQueueNameFormatter.formatRetry(information);
            String deadLetterQueueName = RabbitMqQueueNameFormatter.formatDeadLetter(information);

            Queue queue = QueueBuilder.durable(queueName).build();
            Queue retryQueue = QueueBuilder.durable(retryQueueName).withArguments(
                retryQueueArguments(domainEventsExchange, queueName)
            ).build();
            Queue deadLetterQueue = QueueBuilder.durable(deadLetterQueueName).build();

            Binding fromExchangeSameQueueBinding = BindingBuilder
                .bind(queue)
                .to(domainEventsExchange)
                .with(queueName);

            Binding fromRetryExchangeSameQueueBinding = BindingBuilder
                .bind(retryQueue)
                .to(retryDomainEventsExchange)
                .with(queueName);

            Binding fromDeadLetterExchangeSameQueueBinding = BindingBuilder
                .bind(deadLetterQueue)
                .to(deadLetterDomainEventsExchange)
                .with(queueName);

            List<Binding> fromExchangeDomainEventsBindings = information.subscribedEvents().stream().map(
                domainEventClass -> {
                    String eventName = domainEventsInformation.forClass(domainEventClass);
                    return BindingBuilder
                        .bind(queue)
                        .to(domainEventsExchange)
                        .with(eventName);
                }).collect(Collectors.toList());

            List<Declarable> queuesAndBindings = new ArrayList<>();
            queuesAndBindings.add(queue);
            queuesAndBindings.add(fromExchangeSameQueueBinding);
            queuesAndBindings.addAll(fromExchangeDomainEventsBindings);

            queuesAndBindings.add(retryQueue);
            queuesAndBindings.add(fromRetryExchangeSameQueueBinding);

            queuesAndBindings.add(deadLetterQueue);
            queuesAndBindings.add(fromDeadLetterExchangeSameQueueBinding);

            return queuesAndBindings;
        }).collect(Collectors.toList());
    }

    private HashMap<String, Object> retryQueueArguments(TopicExchange exchange, String routingKey) {
        return new HashMap<String, Object>() {{
            put("x-dead-letter-exchange", exchange.getName());
            put("x-dead-letter-routing-key", routingKey);
            put("x-message-ttl", 1000);
        }};
    }
}
