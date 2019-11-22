package tv.codely.mooc.notifications.application.send_new_courses_newsletter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.application.CoursesResponse;
import tv.codely.mooc.courses.application.CoursesResponseMother;
import tv.codely.mooc.courses.application.search_last.SearchLastCoursesQuery;
import tv.codely.mooc.courses.application.search_last.SearchLastCoursesQueryMother;
import tv.codely.mooc.notifications.application.NotificationsModuleUnitTestCase;
import tv.codely.mooc.notifications.domain.NewCoursesNewsletterEmail;
import tv.codely.mooc.notifications.domain.NewCoursesNewsletterEmailMother;
import tv.codely.mooc.notifications.domain.NewCoursesNewsletterEmailSent;
import tv.codely.mooc.notifications.domain.NewCoursesNewsletterEmailSentMother;
import tv.codely.mooc.students.application.StudentResponse;
import tv.codely.mooc.students.application.StudentResponseMother;
import tv.codely.mooc.students.application.StudentsResponse;
import tv.codely.mooc.students.application.StudentsResponseMother;
import tv.codely.mooc.students.application.search_all.SearchAllStudentsQuery;
import tv.codely.mooc.students.application.search_all.SearchAllStudentsQueryMother;
import tv.codely.shared.domain.bus.command.CommandHandlerExecutionError;
import tv.codely.shared.domain.bus.query.QueryHandlerExecutionError;
import tv.codely.shared.domain.bus.query.QueryNotRegisteredError;

import java.util.Arrays;

final class SendNewCoursesNewsletterCommandHandlerShould extends NotificationsModuleUnitTestCase {
    SendNewCoursesNewsletterCommandHandler handler;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        handler = new SendNewCoursesNewsletterCommandHandler(
            new NewCoursesNewsletterSender(queryBus, uuidGenerator, sender, eventBus)
        );
    }

    @Test
    void not_send_the_newsletter_when_there_are_no_courses() throws QueryNotRegisteredError, QueryHandlerExecutionError, CommandHandlerExecutionError {
        SendNewCoursesNewsletterCommand command = SendNewCoursesNewsletterCommandMother.random();

        SearchLastCoursesQuery coursesQuery    = SearchLastCoursesQueryMother.create(3);
        CoursesResponse        coursesResponse = CoursesResponseMother.empty();

        shouldAsk(coursesQuery, coursesResponse);

        handler.handle(command);
    }

    @Test
    void not_send_the_newsletter_when_there_are_no_students() throws QueryNotRegisteredError, QueryHandlerExecutionError, CommandHandlerExecutionError {
        SendNewCoursesNewsletterCommand command = SendNewCoursesNewsletterCommandMother.random();

        SearchLastCoursesQuery coursesQuery    = SearchLastCoursesQueryMother.create(3);
        CoursesResponse        coursesResponse = CoursesResponseMother.random();

        SearchAllStudentsQuery studentsQuery    = SearchAllStudentsQueryMother.random();
        StudentsResponse       studentsResponse = StudentsResponseMother.empty();

        shouldAsk(coursesQuery, coursesResponse);
        shouldAsk(studentsQuery, studentsResponse);

        handler.handle(command);
    }

    @Test
    void send_the_new_courses_newsletter() throws QueryNotRegisteredError, QueryHandlerExecutionError, CommandHandlerExecutionError {
        SendNewCoursesNewsletterCommand command = SendNewCoursesNewsletterCommandMother.random();

        SearchLastCoursesQuery coursesQuery    = SearchLastCoursesQueryMother.create(3);
        CoursesResponse        coursesResponse = CoursesResponseMother.times(3);

        StudentResponse        student          = StudentResponseMother.random();
        StudentResponse        anotherStudent   = StudentResponseMother.random();
        SearchAllStudentsQuery studentsQuery    = SearchAllStudentsQueryMother.random();
        StudentsResponse       studentsResponse = StudentsResponseMother.create(Arrays.asList(student, anotherStudent));

        NewCoursesNewsletterEmail email = NewCoursesNewsletterEmailMother.create(student, coursesResponse);
        NewCoursesNewsletterEmail anotherEmail = NewCoursesNewsletterEmailMother.create(
            anotherStudent,
            coursesResponse
        );

        NewCoursesNewsletterEmailSent domainEvent = NewCoursesNewsletterEmailSentMother.create(
            email.id(),
            student.id()
        );
        NewCoursesNewsletterEmailSent anotherDomainEvent = NewCoursesNewsletterEmailSentMother.create(
            anotherEmail.id(),
            anotherStudent.id()
        );

        shouldAsk(coursesQuery, coursesResponse);
        shouldAsk(studentsQuery, studentsResponse);

        shouldGenerateUuids(email.id().value(), anotherEmail.id().value());

        handler.handle(command);

        shouldHaveSentEmail(email);
        shouldHaveSentEmail(anotherEmail);

        shouldHavePublished(domainEvent);
        shouldHavePublished(anotherDomainEvent);
    }
}
