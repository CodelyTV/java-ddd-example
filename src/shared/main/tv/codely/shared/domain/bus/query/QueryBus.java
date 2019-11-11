package tv.codely.shared.domain.bus.query;

public interface QueryBus {
    void ask(Query query) throws QueryNotRegisteredError;
}
