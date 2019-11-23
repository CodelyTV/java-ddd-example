package tv.codely.backoffice.courses.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tv.codely.backoffice.BackofficeContextInfrastructureTestCase;
import tv.codely.backoffice.courses.domain.BackofficeCourse;
import tv.codely.backoffice.courses.domain.BackofficeCourseCriteriaMother;
import tv.codely.backoffice.courses.domain.BackofficeCourseMother;
import tv.codely.shared.domain.criteria.Criteria;

import javax.transaction.Transactional;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
class MySqlBackofficeCourseRepositoryShould extends BackofficeContextInfrastructureTestCase {
    @Autowired
    private MySqlBackofficeCourseRepository repository;

    @Test
    void save_a_course() {
        repository.save(BackofficeCourseMother.random());
    }

    @Test
    void search_all_existing_courses() {
        BackofficeCourse course        = BackofficeCourseMother.random();
        BackofficeCourse anotherCourse = BackofficeCourseMother.random();

        repository.save(course);
        repository.save(anotherCourse);

        assertEquals(Arrays.asList(course, anotherCourse), repository.searchAll());
    }

    @Test
    void search_courses_using_a_criteria() {
        BackofficeCourse matchingCourse        = BackofficeCourseMother.create("DDD en Java", "3 days");
        BackofficeCourse anotherMatchingCourse = BackofficeCourseMother.create("DDD en TypeScript", "2.5 days");
        BackofficeCourse intellijCourse        = BackofficeCourseMother.create("Exprimiendo Intellij", "48 hours");
        BackofficeCourse cobolCourse           = BackofficeCourseMother.create("DDD en Cobol", "5 years");

        Criteria criteria = BackofficeCourseCriteriaMother.nameAndDurationContains("DDD", "days");

        repository.save(matchingCourse);
        repository.save(anotherMatchingCourse);
        repository.save(intellijCourse);
        repository.save(cobolCourse);

        assertEquals(Arrays.asList(matchingCourse, anotherMatchingCourse), repository.matching(criteria));
    }
}
