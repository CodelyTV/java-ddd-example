package tv.codely.analytics.domain_events.domain;

public final class AnalyticsDomainEvent {
    private final AnalyticsDomainEventId          id;
    private final AnalyticsDomainEventAggregateId aggregateId;
    private final AnalyticsDomainEventName        name;
    private final AnalyticsDomainEventBody        body;

    public AnalyticsDomainEvent(
        AnalyticsDomainEventId id,
        AnalyticsDomainEventAggregateId aggregateId,
        AnalyticsDomainEventName name,
        AnalyticsDomainEventBody body
    ) {

        this.id          = id;
        this.aggregateId = aggregateId;
        this.name        = name;
        this.body        = body;
    }

    public AnalyticsDomainEventId getId() {
        return id;
    }

    public AnalyticsDomainEventAggregateId getAggregateId() {
        return aggregateId;
    }

    public AnalyticsDomainEventName getName() {
        return name;
    }

    public AnalyticsDomainEventBody getBody() {
        return body;
    }
}
