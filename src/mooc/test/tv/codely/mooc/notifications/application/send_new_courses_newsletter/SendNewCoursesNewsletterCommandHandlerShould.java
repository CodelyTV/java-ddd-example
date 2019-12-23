package tv.codely.mooc.notifications.application.send_new_courses_newsletter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.application.CoursesResponse;
import tv.codely.mooc.courses.application.CoursesResponseMother;
import tv.codely.mooc.courses.application.search_last.SearchLastCoursesQuery;
import tv.codely.mooc.courses.application.search_last.SearchLastCoursesQueryMother;
import tv.codely.mooc.notifications.application.NotificationsModuleUnitTestCase;
import tv.codely.mooc.notifications.domain.NewCoursesNewsletter;
import tv.codely.mooc.notifications.domain.NewCoursesNewsletterEmailSent;
import tv.codely.mooc.notifications.domain.NewCoursesNewsletterEmailSentMother;
import tv.codely.mooc.notifications.domain.NewCoursesNewsletterMother;
import tv.codely.mooc.students.application.StudentResponse;
import tv.codely.mooc.students.application.StudentResponseMother;
import tv.codely.mooc.students.application.StudentsResponse;
import tv.codely.mooc.students.application.StudentsResponseMother;
import tv.codely.mooc.students.application.search_all.SearchAllStudentsQuery;
import tv.codely.mooc.students.application.search_all.SearchAllStudentsQueryMother;
import tv.codely.shared.domain.bus.command.CommandHandlerExecutionError;
import tv.codely.shared.domain.bus.query.QueryHandlerExecutionError;

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
    void not_send_the_newsletter_when_there_are_no_courses() throws QueryHandlerExecutionError, CommandHandlerExecutionError {
        SendNewCoursesNewsletterCommand command = SendNewCoursesNewsletterCommandMother.random();

        SearchLastCoursesQuery coursesQuery    = SearchLastCoursesQueryMother.create(3);
        CoursesResponse        coursesResponse = CoursesResponseMother.empty();

        shouldAsk(coursesQuery, coursesResponse);

        handler.handle(command);
    }

    @Test
    void not_send_the_newsletter_when_there_are_no_students() throws QueryHandlerExecutionError, CommandHandlerExecutionError {
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
    void send_the_new_courses_newsletter() throws QueryHandlerExecutionError, CommandHandlerExecutionError {
        SendNewCoursesNewsletterCommand command = SendNewCoursesNewsletterCommandMother.random();

        SearchLastCoursesQuery coursesQuery    = SearchLastCoursesQueryMother.create(3);
        CoursesResponse        coursesResponse = CoursesResponseMother.times(3);

        SearchAllStudentsQuery studentsQuery    = SearchAllStudentsQueryMother.random();
        StudentResponse        student          = StudentResponseMother.random();
        StudentResponse        otherStudent     = StudentResponseMother.random();
        StudentsResponse       studentsResponse = StudentsResponseMother.create(Arrays.asList(student, otherStudent));

        NewCoursesNewsletter newsletter      = NewCoursesNewsletterMother.create(student, coursesResponse);
        NewCoursesNewsletter otherNewsletter = NewCoursesNewsletterMother.create(otherStudent, coursesResponse);

        NewCoursesNewsletterEmailSent domainEvent = NewCoursesNewsletterEmailSentMother.create(
            newsletter.id(),
            student.id()
        );
        NewCoursesNewsletterEmailSent otherDomainEvent = NewCoursesNewsletterEmailSentMother.create(
            otherNewsletter.id(),
            otherStudent.id()
        );

        shouldAsk(coursesQuery, coursesResponse);
        shouldAsk(studentsQuery, studentsResponse);

        shouldGenerateUuids(newsletter.id().value(), otherNewsletter.id().value());

        handler.handle(command);

        shouldHaveSentEmail(newsletter);
        shouldHavePublished(domainEvent);

        shouldHaveSentEmail(otherNewsletter);
        shouldHavePublished(otherDomainEvent);
    }
}
