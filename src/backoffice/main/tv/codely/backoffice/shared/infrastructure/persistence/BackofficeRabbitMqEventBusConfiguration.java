package tv.codely.backoffice.shared.infrastructure.persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tv.codely.shared.infrastructure.bus.event.mysql.MySqlEventBus;
import tv.codely.shared.infrastructure.bus.event.rabbitmq.RabbitMqEventBus;
import tv.codely.shared.infrastructure.bus.event.rabbitmq.RabbitMqPublisher;

@Configuration
public class BackofficeRabbitMqEventBusConfiguration {
    private final RabbitMqPublisher publisher;
    private final MySqlEventBus     failoverPublisher;

    public BackofficeRabbitMqEventBusConfiguration(
        RabbitMqPublisher publisher,
        @Qualifier("backofficeMysqlEventBus") MySqlEventBus failoverPublisher
    ) {
        this.publisher         = publisher;
        this.failoverPublisher = failoverPublisher;
    }

    @Bean
    public RabbitMqEventBus backofficeRabbitMqEventBus() {
        return new RabbitMqEventBus(publisher, failoverPublisher);
    }
}
