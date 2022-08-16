package tv.codely.analytics.domain_events.domain;

import tv.codely.shared.domain.Identifier;

public final class AnalyticsDomainEventAggregateId extends Identifier {
    public AnalyticsDomainEventAggregateId(String value) {
        super(value);
    }
}
