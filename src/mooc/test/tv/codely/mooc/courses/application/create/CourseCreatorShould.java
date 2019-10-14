package tv.codely.mooc.courses.application.create;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.CoursesModuleUnitTestCase;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEvent;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEventMother;
import tv.codely.mooc.courses.domain.CourseMother;

final class CourseCreatorShould extends CoursesModuleUnitTestCase {
    CourseCreator creator;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        creator = new CourseCreator(repository, eventBus);
    }

    @Test
    void create_a_valid_course() {
        CreateCourseRequest request = CreateCourseRequestMother.random();

        Course                   course      = CourseMother.fromRequest(request);
        CourseCreatedDomainEvent domainEvent = CourseCreatedDomainEventMother.fromCourse(course);

        creator.create(request);

        shouldHaveSaved(course);
        shouldHavePublished(domainEvent);
    }
}
