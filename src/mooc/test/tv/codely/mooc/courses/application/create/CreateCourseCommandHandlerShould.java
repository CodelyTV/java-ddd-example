package tv.codely.mooc.courses.application.create;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.CoursesModuleUnitTestCase;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEventMother;
import tv.codely.mooc.courses.domain.CourseMother;
import tv.codely.shared.domain.course.CourseCreatedDomainEvent;

final class CreateCourseCommandHandlerShould extends CoursesModuleUnitTestCase {
    private CreateCourseCommandHandler handler;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        handler = new CreateCourseCommandHandler(new CourseCreator(repository, eventBus));
    }

    @Test
    void create_a_valid_course() {
        CreateCourseCommand command = CreateCourseCommandMother.random();

        Course                   course      = CourseMother.fromRequest(command);
        CourseCreatedDomainEvent domainEvent = CourseCreatedDomainEventMother.fromCourse(course);

        handler.handle(command);

        shouldHaveSaved(course);
        shouldHavePublished(domainEvent);
    }
}
