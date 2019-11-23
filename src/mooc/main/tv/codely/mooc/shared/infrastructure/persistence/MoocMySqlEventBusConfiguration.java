package tv.codely.mooc.shared.infrastructure.persistence;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tv.codely.shared.infrastructure.bus.event.DomainEventsInformation;
import tv.codely.shared.infrastructure.bus.event.mysql.MySqlDomainEventsConsumer;
import tv.codely.shared.infrastructure.bus.event.mysql.MySqlEventBus;
import tv.codely.shared.infrastructure.bus.event.spring.SpringApplicationEventBus;

@Configuration
public class MoocMySqlEventBusConfiguration {
    private final SessionFactory            sessionFactory;
    private final DomainEventsInformation   domainEventsInformation;
    private final SpringApplicationEventBus bus;

    public MoocMySqlEventBusConfiguration(
        SessionFactory sessionFactory,
        DomainEventsInformation domainEventsInformation,
        SpringApplicationEventBus bus
    ) {
        this.sessionFactory          = sessionFactory;
        this.domainEventsInformation = domainEventsInformation;
        this.bus                     = bus;
    }

    @Bean
    public MySqlEventBus mysqlEventBus() {
        return new MySqlEventBus(sessionFactory);
    }

    @Bean
    public MySqlDomainEventsConsumer mySqlDomainEventsConsumer() {
        return new MySqlDomainEventsConsumer(sessionFactory, domainEventsInformation, bus);
    }
}
