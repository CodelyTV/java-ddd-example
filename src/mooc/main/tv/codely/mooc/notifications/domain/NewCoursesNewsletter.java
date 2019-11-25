package tv.codely.mooc.notifications.domain;

import tv.codely.mooc.courses.application.CoursesResponse;
import tv.codely.mooc.students.application.StudentResponse;

import java.util.Objects;

public final class NewCoursesNewsletter extends Email {
    private final StudentResponse student;
    private final CoursesResponse courses;

    public NewCoursesNewsletter(EmailId id, StudentResponse student, CoursesResponse courses) {
        super(id, "news@codely.tv", student.email(), "Último cursos en CodelyTV", formatBody(student, courses));

        this.student = student;
        this.courses = courses;
    }

    private static String formatBody(StudentResponse student, CoursesResponse courses) {
        return String.format(
            "Hoy es tu día de suerte... %s vas a ver %s nuevos cursos",
            student.name(),
            courses.courses().size()
        );
    }

    public static NewCoursesNewsletter send(String id, StudentResponse student, CoursesResponse courses) {
        NewCoursesNewsletter newsletter = new NewCoursesNewsletter(new EmailId(id), student, courses);

        newsletter.record(new NewCoursesNewsletterEmailSent(id, student.id()));

        return newsletter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        NewCoursesNewsletter that = (NewCoursesNewsletter) o;
        return student.equals(that.student) &&
               courses.equals(that.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), student, courses);
    }
}
