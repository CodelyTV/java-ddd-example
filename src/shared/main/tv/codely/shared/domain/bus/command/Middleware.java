package tv.codely.shared.domain.bus.command;

public interface Middleware {
    void handle(Command command);
}
