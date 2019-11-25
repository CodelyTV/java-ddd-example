package tv.codely.mooc.courses_counter.application.increment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEventMother;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseIdMother;
import tv.codely.mooc.courses_counter.CoursesCounterModuleUnitTestCase;
import tv.codely.mooc.courses_counter.domain.CoursesCounter;
import tv.codely.mooc.courses_counter.domain.CoursesCounterMother;
import tv.codely.shared.domain.course.CourseCreatedDomainEvent;

final class IncrementCoursesCounterOnCourseCreatedShould extends CoursesCounterModuleUnitTestCase {
    IncrementCoursesCounterOnCourseCreated subscriber;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        subscriber = new IncrementCoursesCounterOnCourseCreated(
            new CoursesCounterIncrementer(repository, uuidGenerator)
        );
    }

    @Test
    void it_should_initialize_a_new_counter() {
        CourseCreatedDomainEvent event = CourseCreatedDomainEventMother.random();

        CourseId       courseId   = CourseIdMother.create(event.aggregateId());
        CoursesCounter newCounter = CoursesCounterMother.withOne(courseId);

        shouldSearch();
        shouldGenerateUuid(newCounter.id().value());

        subscriber.on(event);

        shouldHaveSaved(newCounter);
    }

    @Test
    void it_should_increment_an_existing_counter() {
        CourseCreatedDomainEvent event = CourseCreatedDomainEventMother.random();

        CourseId       courseId           = CourseIdMother.create(event.aggregateId());
        CoursesCounter existingCounter    = CoursesCounterMother.random();
        CoursesCounter incrementedCounter = CoursesCounterMother.incrementing(existingCounter, courseId);

        shouldSearch(existingCounter);

        subscriber.on(event);

        shouldHaveSaved(incrementedCounter);
    }

    @Test
    void it_should_not_increment_an_already_incremented_course() {
        CourseCreatedDomainEvent event = CourseCreatedDomainEventMother.random();

        CourseId       courseId        = CourseIdMother.create(event.aggregateId());
        CoursesCounter existingCounter = CoursesCounterMother.withOne(courseId);

        shouldSearch(existingCounter);

        subscriber.on(event);
    }
}
