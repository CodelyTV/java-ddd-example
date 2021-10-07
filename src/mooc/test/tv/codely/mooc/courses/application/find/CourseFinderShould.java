package tv.codely.mooc.courses.application.find;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.CoursesModuleUnitTestCase;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseMother;
import tv.codely.mooc.courses.domain.CourseNotExist;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

final class CourseFinderShould extends CoursesModuleUnitTestCase {
    private CourseFinder finder;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        finder = new CourseFinder(repository);
    }

    @Test
    void should_find_existing_course() {
        Course expectedCourse = CourseMother.random();
        CourseId id = expectedCourse.id();

        when(repository.search(id)).thenReturn(Optional.of(expectedCourse));
        finder.find(id);

        shouldHaveSearched(id);
    }

    @Test
    void should_fail_if_course_doesnt_exists() {
        Course nonexistentCourse = CourseMother.random();

        assertThrows(CourseNotExist.class, () -> finder.find(nonexistentCourse.id()));
    }
}
