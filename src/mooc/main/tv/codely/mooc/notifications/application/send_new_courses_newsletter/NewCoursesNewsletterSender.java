package tv.codely.mooc.notifications.application.send_new_courses_newsletter;

import tv.codely.mooc.courses.application.CoursesResponse;
import tv.codely.mooc.courses.application.search_last.SearchLastCoursesQuery;
import tv.codely.mooc.notifications.domain.EmailSender;
import tv.codely.mooc.notifications.domain.NewCoursesNewsletterEmail;
import tv.codely.mooc.students.application.StudentResponse;
import tv.codely.mooc.students.application.StudentsResponse;
import tv.codely.mooc.students.application.search_all.SearchAllStudentsQuery;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.UuidGenerator;
import tv.codely.shared.domain.bus.event.EventBus;
import tv.codely.shared.domain.bus.query.QueryBus;
import tv.codely.shared.domain.bus.query.QueryHandlerExecutionError;
import tv.codely.shared.domain.bus.query.QueryNotRegisteredError;

@Service
public final class NewCoursesNewsletterSender {
    private final static Integer       TOTAL_COURSES = 3;
    private final        QueryBus      queryBus;
    private final        EmailSender   sender;
    private              UuidGenerator uuidGenerator;
    private              EventBus      eventBus;

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

    public void send() throws QueryNotRegisteredError, QueryHandlerExecutionError {
        StudentsResponse students = queryBus.ask(new SearchAllStudentsQuery());
        CoursesResponse  courses  = queryBus.ask(new SearchLastCoursesQuery(TOTAL_COURSES));

        students.students().forEach(student -> send(student, courses));
    }

    public void send(StudentResponse student, CoursesResponse courses) {
        NewCoursesNewsletterEmail email = NewCoursesNewsletterEmail.send(uuidGenerator.generate(), student, courses);

        sender.send(email);

        eventBus.publish(email.pullDomainEvents());
    }
}
