package tv.codely.mooc.shared.infrastructure.bus.event.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tv.codely.mooc.MoocContextInfrastructureTestCase;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEvent;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEventMother;
import tv.codely.shared.infrastructure.bus.event.rabbitmq.RabbitMqDomainEventsConsumer;
import tv.codely.shared.infrastructure.bus.event.rabbitmq.RabbitMqEventBus;

import java.util.Collections;

public final class RabbitMqEventBusShould extends MoocContextInfrastructureTestCase {
    @Autowired
    private RabbitMqEventBus             eventBus;
    @Autowired
    private RabbitMqDomainEventsConsumer consumer;

    @Test
    void publish_and_consume_domain_events_from_rabbitmq() {
        CourseCreatedDomainEvent domainEvent = CourseCreatedDomainEventMother.random();

        eventBus.publish(Collections.singletonList(domainEvent));

        consumer.consume();
    }
}
