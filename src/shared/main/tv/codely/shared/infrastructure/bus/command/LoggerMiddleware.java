package tv.codely.shared.infrastructure.bus.command;

import tv.codely.shared.domain.Logger;
import tv.codely.shared.domain.bus.command.Command;
import tv.codely.shared.domain.bus.command.Middleware;

import java.util.Optional;

public class LoggerMiddleware implements Middleware {

    private final Logger logger;
    private final Optional<Middleware> next;

    LoggerMiddleware(Logger logger, Middleware next) {
        this.logger = logger;
        this.next = Optional.of(next);
    }

    LoggerMiddleware(Logger logger) {
        this.logger = logger;
        this.next = Optional.empty();
    }

    @Override
    public void handle(Command command) {
        logger.info("lasdl");
        next.map(middleware -> middleware.handle(command));
    }
}
