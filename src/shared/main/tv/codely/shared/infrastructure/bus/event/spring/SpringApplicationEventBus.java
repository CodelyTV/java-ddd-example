package tv.codely.shared.infrastructure.bus.event.spring;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Primary;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.domain.bus.event.EventBus;

import java.util.List;

@Primary
@Service
public class SpringApplicationEventBus implements EventBus {
    private final ApplicationEventPublisher publisher;

    public SpringApplicationEventBus(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(final List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    private void publish(final DomainEvent event) {
        this.publisher.publishEvent(event);
    }
}
