package tv.codely.shared.domain.bus.query;

public interface QueryHandler<Q extends Query<R>, R extends Response> {
    R handle(Q query);
}
