package tv.codely.analytics.domain_events.domain;

public interface DomainEventsRepository {
    void save(AnalyticsDomainEvent event);
}
