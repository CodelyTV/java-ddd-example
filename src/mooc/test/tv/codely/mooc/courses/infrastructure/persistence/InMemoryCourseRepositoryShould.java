package tv.codely.mooc.courses.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseId;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

final class InMemoryCourseRepositoryShould {
    @Test
    void save_a_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();
        Course                   course     = new Course(
            new CourseId("decf33ca-81a7-419f-a07a-74f214e928e5"),
            "name",
            "duration"
        );

        repository.save(course);
    }

    @Test
    void return_an_existing_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();
        Course                   course     = new Course(
            new CourseId("decf33ca-81a7-419f-a07a-74f214e928e5"),
            "name",
            "duration"
        );

        repository.save(course);

        assertEquals(Optional.of(course), repository.search(course.id()));
    }

    @Test
    void not_return_a_non_existing_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();

        assertFalse(repository.search(new CourseId("65cc2174-30bf-4630-9392-f8084f088cc6")).isPresent());
    }
}
