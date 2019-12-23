package tv.codely.mooc.notifications.application.send_new_courses_newsletter;

import tv.codely.mooc.courses.application.CoursesResponse;
import tv.codely.mooc.courses.application.search_last.SearchLastCoursesQuery;
import tv.codely.mooc.notifications.domain.EmailSender;
import tv.codely.mooc.notifications.domain.NewCoursesNewsletter;
import tv.codely.mooc.students.application.StudentResponse;
import tv.codely.mooc.students.application.StudentsResponse;
import tv.codely.mooc.students.application.search_all.SearchAllStudentsQuery;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.UuidGenerator;
import tv.codely.shared.domain.bus.event.EventBus;
import tv.codely.shared.domain.bus.query.QueryBus;

@Service
public final class NewCoursesNewsletterSender {
    private final static Integer       TOTAL_COURSES = 3;
    private final        QueryBus      queryBus;
    private final        EmailSender   sender;
    private final        UuidGenerator uuidGenerator;
    private final        EventBus      eventBus;

    public NewCoursesNewsletterSender(
        QueryBus queryBus,
        UuidGenerator uuidGenerator,
        EmailSender sender,
        EventBus eventBus
    ) {
        this.queryBus      = queryBus;
        this.uuidGenerator = uuidGenerator;
        this.sender        = sender;
        this.eventBus      = eventBus;
    }

    public void send() {
        CoursesResponse courses = queryBus.ask(new SearchLastCoursesQuery(TOTAL_COURSES));

        if (courses.courses().size() > 0) {
            StudentsResponse students = queryBus.ask(new SearchAllStudentsQuery());

            students.students().forEach(student -> send(student, courses));
        }
    }

    public void send(StudentResponse student, CoursesResponse courses) {
        NewCoursesNewsletter newsletter = NewCoursesNewsletter.send(uuidGenerator.generate(), student, courses);

        sender.send(newsletter);

        eventBus.publish(newsletter.pullDomainEvents());
    }
}
