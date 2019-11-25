package tv.codely.mooc.notifications.domain;

import tv.codely.mooc.students.domain.StudentIdMother;

public final class NewCoursesNewsletterEmailSentMother {
    public static NewCoursesNewsletterEmailSent create(EmailId id, String studentId) {
        return new NewCoursesNewsletterEmailSent(id.value(), studentId);
    }

    public static NewCoursesNewsletterEmailSent random() {
        return create(EmailIdMother.random(), StudentIdMother.random().value());
    }
}
