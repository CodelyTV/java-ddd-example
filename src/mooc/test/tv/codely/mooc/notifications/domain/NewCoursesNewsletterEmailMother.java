package tv.codely.mooc.notifications.domain;

import tv.codely.mooc.courses.application.CoursesResponse;
import tv.codely.mooc.courses.application.CoursesResponseMother;
import tv.codely.mooc.students.application.StudentResponse;
import tv.codely.mooc.students.application.StudentResponseMother;

public final class NewCoursesNewsletterEmailMother {
    public static NewCoursesNewsletterEmail create(EmailId id, StudentResponse student, CoursesResponse courses) {
        return new NewCoursesNewsletterEmail(id, student, courses);
    }

    public static NewCoursesNewsletterEmail create(StudentResponse student, CoursesResponse courses) {
        return new NewCoursesNewsletterEmail(EmailIdMother.random(), student, courses);
    }

    public static NewCoursesNewsletterEmail random() {
        return create(EmailIdMother.random(), StudentResponseMother.random(), CoursesResponseMother.random());
    }
}
