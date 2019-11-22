package tv.codely.shared.domain.bus.query;

public final class QueryHandlerExecutionError extends Throwable {
    public QueryHandlerExecutionError(Throwable cause) {
        super(cause);
    }
}
