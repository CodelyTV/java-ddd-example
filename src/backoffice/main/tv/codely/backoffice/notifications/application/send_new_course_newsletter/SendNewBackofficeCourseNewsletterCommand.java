package tv.codely.backoffice.notifications.application.send_new_course_newsletter;

import tv.codely.shared.domain.bus.command.Command;

public final class SendNewBackofficeCourseNewsletterCommand implements Command {
    private final String courseName;

    public SendNewBackofficeCourseNewsletterCommand(String courseName) {
        this.courseName = courseName;
    }

    public String courseName() {
        return this.courseName;
    }
}
