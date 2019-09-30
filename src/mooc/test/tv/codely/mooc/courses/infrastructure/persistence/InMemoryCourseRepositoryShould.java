package tv.codely.mooc.courses.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.CoursesModuleInfrastructureTestCase;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseIdMother;
import tv.codely.mooc.courses.domain.CourseMother;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

final class InMemoryCourseRepositoryShould extends CoursesModuleInfrastructureTestCase {
    @Test
    void save_a_course() {
        Course course = CourseMother.random();

        repository.save(course);
    }

    @Test
    void return_an_existing_course() {
        Course course = CourseMother.random();

        repository.save(course);

        assertEquals(Optional.of(course), repository.search(course.id()));
    }

    @Test
    void not_return_a_non_existing_course() {
        assertFalse(repository.search(CourseIdMother.random()).isPresent());
    }
}
