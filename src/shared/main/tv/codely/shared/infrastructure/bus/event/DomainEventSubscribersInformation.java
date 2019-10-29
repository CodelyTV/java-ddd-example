package tv.codely.shared.infrastructure.bus.event;

import org.reflections.Reflections;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.event.DomainEventSubscriber;

import java.util.*;

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
