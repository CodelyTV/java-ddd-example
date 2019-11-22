package tv.codely.mooc.notifications.application.send_new_courses_newsletter;

import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.command.CommandHandler;
import tv.codely.shared.domain.bus.command.CommandHandlerExecutionError;

@Service
public final class SendNewCoursesNewsletterCommandHandler implements CommandHandler<SendNewCoursesNewsletterCommand> {
    private final NewCoursesNewsletterSender sender;

    public SendNewCoursesNewsletterCommandHandler(NewCoursesNewsletterSender sender) {
        this.sender = sender;
    }

    @Override
    public void handle(SendNewCoursesNewsletterCommand command) throws CommandHandlerExecutionError {
        try {
            sender.send();
        } catch (Throwable error) {
            throw new CommandHandlerExecutionError(error);
        }
    }
}
