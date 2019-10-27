package tv.codely.shared.infrastructure.bus.event.rabbitmq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

interface RabbtMqDomainEventsBinding {
    @Output("domain_events")
    MessageChannel domainEvents();
}
