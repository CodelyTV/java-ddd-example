package tv.codely.mooc.application.create;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.domain.CourseRepository;
import tv.codely.mooc.domain.Course;

import static org.mockito.Mockito.*;

class CourseCreatorShould {

    @Test
    void save_a_valid_course() throws Exception {
        CourseRepository repository = mock(CourseRepository.class);
        CourseCreator creator = new CourseCreator(repository);

        Course course = new Course("1", "courseName", "1");
        creator.create(course.getId(), course.getName(), course.getDuration());

        verify(repository, atLeastOnce()).save(course);
    }
}
