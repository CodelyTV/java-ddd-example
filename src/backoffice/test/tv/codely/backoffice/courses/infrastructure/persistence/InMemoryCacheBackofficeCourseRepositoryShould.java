package tv.codely.backoffice.courses.infrastructure.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tv.codely.backoffice.BackofficeContextInfrastructureTestCase;
import tv.codely.backoffice.courses.domain.BackofficeCourse;
import tv.codely.backoffice.courses.domain.BackofficeCourseCriteriaMother;
import tv.codely.backoffice.courses.domain.BackofficeCourseMother;
import tv.codely.backoffice.courses.domain.BackofficeCourseRepository;
import tv.codely.shared.domain.criteria.Criteria;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.*;

final class InMemoryCacheBackofficeCourseRepositoryShould extends BackofficeContextInfrastructureTestCase {
    private InMemoryCacheBackofficeCourseRepository repository;
    private BackofficeCourseRepository              innerRepository;

    @BeforeEach
    protected void setUp() {
        innerRepository = mock(BackofficeCourseRepository.class);
        repository      = new InMemoryCacheBackofficeCourseRepository(innerRepository);
    }

    @Test
    void save_a_course() {
        BackofficeCourse course = BackofficeCourseMother.random();

        repository.save(course);

        shouldHaveSaved(course);
    }

    @Test
    void search_all_existing_courses() {
        BackofficeCourse       course        = BackofficeCourseMother.random();
        BackofficeCourse       anotherCourse = BackofficeCourseMother.random();
        List<BackofficeCourse> courses       = Arrays.asList(course, anotherCourse);

        shouldSearchAll(courses);

        assertThat(courses, containsInAnyOrder(repository.searchAll().toArray()));
    }

    @Test
    void search_all_existing_courses_without_hitting_the_inner_repository_the_second_time() {
        BackofficeCourse       course        = BackofficeCourseMother.random();
        BackofficeCourse       anotherCourse = BackofficeCourseMother.random();
        List<BackofficeCourse> courses       = Arrays.asList(course, anotherCourse);

        shouldSearchAll(courses);

        assertThat(courses, containsInAnyOrder(repository.searchAll().toArray()));
        assertThat(courses, containsInAnyOrder(repository.searchAll().toArray()));
    }

    @Test
    void search_courses_using_a_criteria() {
        BackofficeCourse       matchingCourse        = BackofficeCourseMother.create("DDD en Java", "3 days");
        BackofficeCourse       anotherMatchingCourse = BackofficeCourseMother.create("DDD en TypeScript", "2.5 days");
        List<BackofficeCourse> matchingCourses       = Arrays.asList(matchingCourse, anotherMatchingCourse);

        Criteria criteria = BackofficeCourseCriteriaMother.nameAndDurationContains("DDD", "days");

        shouldSearchMatching(criteria, matchingCourses);

        assertThat(matchingCourses, containsInAnyOrder(repository.matching(criteria).toArray()));
    }

    @Test
    void search_courses_using_a_criteria_without_hitting_the_inner_repository_the_second_time() {
        BackofficeCourse       matchingCourse        = BackofficeCourseMother.create("DDD en Java", "3 days");
        BackofficeCourse       anotherMatchingCourse = BackofficeCourseMother.create("DDD en TypeScript", "2.5 days");
        List<BackofficeCourse> matchingCourses       = Arrays.asList(matchingCourse, anotherMatchingCourse);

        Criteria criteria = BackofficeCourseCriteriaMother.nameAndDurationContains("DDD", "days");

        shouldSearchMatching(criteria, matchingCourses);

        assertThat(matchingCourses, containsInAnyOrder(repository.matching(criteria).toArray()));
        assertThat(matchingCourses, containsInAnyOrder(repository.matching(criteria).toArray()));
    }

    private void shouldSearchAll(List<BackofficeCourse> courses) {
        Mockito.when(innerRepository.searchAll()).thenReturn(courses);
    }

    private void shouldSearchMatching(Criteria criteria, List<BackofficeCourse> courses) {
        Mockito.when(innerRepository.matching(criteria)).thenReturn(courses);
    }

    private void shouldHaveSaved(BackofficeCourse course) {
        verify(innerRepository, atLeastOnce()).save(course);
    }
}
