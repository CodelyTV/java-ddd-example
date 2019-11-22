package tv.codely.shared.domain.bus.command;

public final class CommandHandlerExecutionError extends Throwable {
    public CommandHandlerExecutionError(Throwable cause) {
        super(cause);
    }
}
