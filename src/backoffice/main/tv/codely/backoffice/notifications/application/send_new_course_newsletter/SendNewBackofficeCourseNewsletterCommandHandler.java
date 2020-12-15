package tv.codely.backoffice.notifications.application.send_new_course_newsletter;

import tv.codely.shared.domain.bus.command.CommandHandler;

public final class SendNewBackofficeCourseNewsletterCommandHandler implements CommandHandler<SendNewBackofficeCourseNewsletterCommand> {
    private final NewBackofficeCourseNewsletterSender sender;

    public SendNewBackofficeCourseNewsletterCommandHandler(NewBackofficeCourseNewsletterSender sender) {
        this.sender = sender;
    }

    @Override
    public void handle(SendNewBackofficeCourseNewsletterCommand command) {
        this.sender.send(command.courseName());
    }
}
