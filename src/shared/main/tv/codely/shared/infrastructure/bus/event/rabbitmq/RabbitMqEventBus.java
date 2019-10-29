package tv.codely.shared.infrastructure.bus.event.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.domain.bus.event.EventBus;
import tv.codely.shared.infrastructure.bus.event.DomainEventJsonSerializer;

import java.util.List;

@Service
public final class RabbitMqEventBus implements EventBus {
    private final RabbitTemplate rabbitTemplate;

    public RabbitMqEventBus(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void publish(List<DomainEvent<?>> events) {
        events.forEach(this::publish);
    }

    private void publish(DomainEvent<?> domainEvent) {
        String serializedDomainEvent = DomainEventJsonSerializer.serialize(domainEvent);

        rabbitTemplate.convertAndSend(domainEvent.eventName(), serializedDomainEvent);
    }
}
