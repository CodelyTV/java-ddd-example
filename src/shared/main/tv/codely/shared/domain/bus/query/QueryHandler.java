package tv.codely.shared.domain.bus.query;

public interface QueryHandler<T extends Query> {
    void handle(T query);
}
