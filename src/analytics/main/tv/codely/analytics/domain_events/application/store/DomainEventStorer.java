package tv.codely.analytics.domain_events.application.store;

import tv.codely.analytics.domain_events.domain.*;

public final class DomainEventStorer {
    private DomainEventsRepository repository;

    public DomainEventStorer(DomainEventsRepository repository) {
        this.repository = repository;
    }

    public void store(
        AnalyticsDomainEventId id,
        AnalyticsDomainEventAggregateId aggregateId,
        AnalyticsDomainEventName name,
        AnalyticsDomainEventBody body
    ) {
        AnalyticsDomainEvent domainEvent = new AnalyticsDomainEvent(id, aggregateId, name, body);

        repository.save(domainEvent);
    }
}
