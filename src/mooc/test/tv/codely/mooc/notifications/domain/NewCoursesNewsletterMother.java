package tv.codely.mooc.notifications.domain;

import tv.codely.mooc.courses.application.CoursesResponse;
import tv.codely.mooc.courses.application.CoursesResponseMother;
import tv.codely.mooc.students.application.StudentResponse;
import tv.codely.mooc.students.application.StudentResponseMother;

public final class NewCoursesNewsletterMother {
    public static NewCoursesNewsletter create(EmailId id, StudentResponse student, CoursesResponse courses) {
        return new NewCoursesNewsletter(id, student, courses);
    }

    public static NewCoursesNewsletter create(StudentResponse student, CoursesResponse courses) {
        return new NewCoursesNewsletter(EmailIdMother.random(), student, courses);
    }

    public static NewCoursesNewsletter random() {
        return create(EmailIdMother.random(), StudentResponseMother.random(), CoursesResponseMother.random());
    }
}
