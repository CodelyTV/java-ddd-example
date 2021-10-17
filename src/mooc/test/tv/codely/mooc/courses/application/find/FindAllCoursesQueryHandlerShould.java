package tv.codely.mooc.courses.application.find;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.CoursesModuleUnitTestCase;
import tv.codely.mooc.courses.application.CourseResponseMother;
import tv.codely.mooc.courses.application.CoursesResponse;
import tv.codely.mooc.courses.application.CoursesResponseMother;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseMother;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public final class FindAllCoursesQueryHandlerShould extends CoursesModuleUnitTestCase {
    private FindAllCoursesQueryHandler handler;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        handler = new FindAllCoursesQueryHandler(new AllCoursesFinder(repository));
    }

    @Test
    void should_find_all_courses() {
        List<Course> courses = List.of(CourseMother.random(), CourseMother.random());
        CoursesResponse expectedResponse =
            CoursesResponseMother.create(CourseResponseMother.create(courses));
        FindAllCoursesQuery query = FindAllCoursesQueryMother.random();

        when(repository.findAll()).thenReturn(courses);
        CoursesResponse response = handler.handle(query);

        shouldHaveFindAll();
        assertEquals(response, expectedResponse);
    }
}
