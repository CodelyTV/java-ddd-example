package tv.codely.mooc.notifications.application.send_new_courses_newsletter;

import tv.codely.shared.domain.UuidMother;

public final class SendNewCoursesNewsletterCommandMother {
    public static SendNewCoursesNewsletterCommand random() {
        return new SendNewCoursesNewsletterCommand(UuidMother.random());
    }
}
