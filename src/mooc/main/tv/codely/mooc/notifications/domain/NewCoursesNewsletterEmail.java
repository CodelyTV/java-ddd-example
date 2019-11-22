package tv.codely.mooc.notifications.domain;

import tv.codely.mooc.courses.application.CoursesResponse;
import tv.codely.mooc.students.application.StudentResponse;

public final class NewCoursesNewsletterEmail extends Email {
    public NewCoursesNewsletterEmail(EmailId id, StudentResponse student, CoursesResponse courses) {
        super(id, "news@codely.tv", student.email(), "Último cursos en CodelyTV", formatBody(student, courses));
    }

    private static String formatBody(StudentResponse student, CoursesResponse courses) {
        return String.format(
            "Hoy es tu día de suerte... %s vas a ver %s nuevos cursos",
            student.name(),
            courses.courses().size()
        );
    }

    public static NewCoursesNewsletterEmail send(String id, StudentResponse student, CoursesResponse courses) {
        NewCoursesNewsletterEmail email = new NewCoursesNewsletterEmail(new EmailId(id), student, courses);

        email.record(new NewCoursesNewsletterEmailSent(id, student.id()));

        return email;
    }
}
