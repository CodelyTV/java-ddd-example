package tv.codely.mooc.infrastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.domain.Course;

import java.util.Optional;

class InMemoryCourseRepositoryShould {

    @Test
    void save_a_valid_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();

        repository.save(new Course("1", "courseName", "2"));
    }

    @Test
    void search_an_existing_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();

        Course course = new Course("1", "courseName", "2");

        repository.save(course);

        Assertions.assertEquals(Optional.of(course), repository.search(course.getId()));
    }

    @Test
    void search_and_not_find_a_course() {
        InMemoryCourseRepository repository = new InMemoryCourseRepository();

        Assertions.assertFalse(repository.search("non_existing_course").isPresent());
    }
}
