package tv.codely.mooc.courses.application.create;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseRepository;

import static org.mockito.Mockito.*;

final class CourseCreatorShould {
    @Test
    void create_a_valid_course() {
        CourseRepository repository = mock(CourseRepository.class);
        CourseCreator    creator    = new CourseCreator(repository);

        String id       = "some-id";
        String name     = "name";
        String duration = "duration";

        Course course = new Course(id, name, duration);

        creator.create(course.id(), course.name(), course.duration());

        verify(repository, atLeastOnce()).save(course);
    }
}
