package tv.codely.shared.infrastructure.bus.command;

import org.springframework.context.ApplicationContext;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.command.*;

@Service
public final class InMemoryCommandBus implements CommandBus {
    private final CommandHandlersInformation information;
    private final ApplicationContext         context;

    public InMemoryCommandBus(CommandHandlersInformation information, ApplicationContext context) {
        this.information = information;
        this.context     = context;
    }

    @Override
    public void dispatch(Command command) throws CommandNotRegisteredError, CommandHandlerExecutionError {
        Class<? extends CommandHandler> commandHandlerClass = information.search(command.getClass());

        CommandHandler handler = context.getBean(commandHandlerClass);

        handler.handle(command);
    }
}
