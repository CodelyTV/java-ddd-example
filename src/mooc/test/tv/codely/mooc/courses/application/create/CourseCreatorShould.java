package tv.codely.mooc.courses.application.create;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.domain.*;

import static org.mockito.Mockito.*;

final class CourseCreatorShould {
    @Test
    void create_a_valid_course() {
        CourseRepository repository = mock(CourseRepository.class);
        CourseCreator    creator    = new CourseCreator(repository);

        CreateCourseRequest request = new CreateCourseRequest(
            "decf33ca-81a7-419f-a07a-74f214e928e5",
            "name",
            "duration"
        );

        Course course = new Course(
            new CourseId(request.id()),
            new CourseName(request.name()),
            new CourseDuration(request.duration())
        );

        creator.create(request);

        verify(repository, atLeastOnce()).save(course);
    }
}
