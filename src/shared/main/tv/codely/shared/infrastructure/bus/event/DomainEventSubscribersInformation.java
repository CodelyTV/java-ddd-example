package tv.codely.shared.infrastructure.bus.event;

import org.reflections.Reflections;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.event.DomainEventSubscriber;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public final class DomainEventSubscribersInformation {
    HashMap<Class<?>, DomainEventSubscriberInformation> information;

    public DomainEventSubscribersInformation() {
        Reflections   reflections = new Reflections("tv.codely");
        Set<Class<?>> classes     = reflections.getTypesAnnotatedWith(DomainEventSubscriber.class);

        information = formatSubscribers(classes);
    }

    public Collection<DomainEventSubscriberInformation> all() {
        return information.values();
    }

    public String[] rabbitMqFormattedNames() {
        return information.values()
                          .stream()
                          .map(DomainEventSubscriberInformation::formatRabbitMqQueueName)
                          .distinct()
                          .toArray(String[]::new);
    }

    private HashMap<Class<?>, DomainEventSubscriberInformation> formatSubscribers(Set<Class<?>> subscribers) {
        HashMap<Class<?>, DomainEventSubscriberInformation> subscribersInformation = new HashMap<>();

        for (Class<?> subscriberClass : subscribers) {
            DomainEventSubscriber annotation = subscriberClass.getAnnotation(DomainEventSubscriber.class);

            subscribersInformation.put(
                subscriberClass,
                new DomainEventSubscriberInformation(subscriberClass, Arrays.asList(annotation.value()))
            );
        }

        return subscribersInformation;
    }
}
