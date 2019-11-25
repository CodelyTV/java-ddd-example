package tv.codely.mooc.shared.infrastructure.bus.event.mysql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tv.codely.mooc.MoocContextInfrastructureTestCase;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEventMother;
import tv.codely.shared.domain.course.CourseCreatedDomainEvent;
import tv.codely.shared.infrastructure.bus.event.mysql.MySqlDomainEventsConsumer;
import tv.codely.shared.infrastructure.bus.event.mysql.MySqlEventBus;

import javax.transaction.Transactional;
import java.util.Collections;

@Transactional
class MySqlEventBusShould extends MoocContextInfrastructureTestCase {
    @Autowired
    private MySqlEventBus             eventBus;
    @Autowired
    private MySqlDomainEventsConsumer consumer;

    @Test
    void publish_and_consume_domain_events_from_msql() throws InterruptedException {
        CourseCreatedDomainEvent domainEvent = CourseCreatedDomainEventMother.random();

        eventBus.publish(Collections.singletonList(domainEvent));

        Thread consumerProcess = new Thread(() -> consumer.consume());
        consumerProcess.start();

        Thread.sleep(100);

        consumer.stop();
    }
}
