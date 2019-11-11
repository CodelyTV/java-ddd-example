package tv.codely.shared.infrastructure.bus.query;

import org.springframework.context.ApplicationContext;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.query.Query;
import tv.codely.shared.domain.bus.query.QueryBus;
import tv.codely.shared.domain.bus.query.QueryHandler;
import tv.codely.shared.domain.bus.query.QueryNotRegisteredError;

@Service
public final class InMemoryQueryBus implements QueryBus {
    private final QueryHandlersInformation information;
    private final ApplicationContext       context;

    public InMemoryQueryBus(QueryHandlersInformation information, ApplicationContext context) {
        this.information = information;
        this.context     = context;
    }

    @Override
    public void ask(Query query) throws QueryNotRegisteredError {
        Class<? extends QueryHandler> queryHandlerClass = information.search(query.getClass());

        QueryHandler handler = context.getBean(queryHandlerClass);

        handler.handle(query);
    }
}
