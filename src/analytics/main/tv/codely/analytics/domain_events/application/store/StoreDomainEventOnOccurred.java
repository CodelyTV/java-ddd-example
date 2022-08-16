package tv.codely.analytics.domain_events.application.store;

import org.springframework.context.event.EventListener;
import tv.codely.analytics.domain_events.domain.AnalyticsDomainEventAggregateId;
import tv.codely.analytics.domain_events.domain.AnalyticsDomainEventBody;
import tv.codely.analytics.domain_events.domain.AnalyticsDomainEventId;
import tv.codely.analytics.domain_events.domain.AnalyticsDomainEventName;
import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.domain.bus.event.DomainEventSubscriber;

@DomainEventSubscriber({DomainEvent.class})
public final class StoreDomainEventOnOccurred {
    private final DomainEventStorer storer;

    public StoreDomainEventOnOccurred(DomainEventStorer storer) {
        this.storer = storer;
    }

    @EventListener
    public void on(DomainEvent event) {
        AnalyticsDomainEventId          id          = new AnalyticsDomainEventId(event.eventId());
        AnalyticsDomainEventAggregateId aggregateId = new AnalyticsDomainEventAggregateId(event.aggregateId());
        AnalyticsDomainEventName        name        = new AnalyticsDomainEventName(event.eventName());
        AnalyticsDomainEventBody        body        = new AnalyticsDomainEventBody(event.toPrimitives());

        storer.store(id, aggregateId, name, body);
    }
}
