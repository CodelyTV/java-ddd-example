package tv.codely.shared.infrastructure.bus.event.rabbitmq;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.domain.bus.event.EventBus;
import tv.codely.shared.infrastructure.bus.event.DomainEventJsonSerializer;

import java.util.List;

@Service
public final class RabbitMqEventBus implements EventBus {
    private final RabbtMqDomainEventsBinding binding;

    public RabbitMqEventBus(RabbtMqDomainEventsBinding binding) {
        this.binding = binding;
    }

    @Override
    public void publish(List<DomainEvent<?>> events) {
        events.forEach(this::publish);
    }

    private void publish(DomainEvent<?> domainEvent) {
        String serializedDomainEvent = DomainEventJsonSerializer.serialize(domainEvent);

        Message<String> message = MessageBuilder.withPayload(serializedDomainEvent).build();

        binding.domainEvents().send(message);
    }
}
