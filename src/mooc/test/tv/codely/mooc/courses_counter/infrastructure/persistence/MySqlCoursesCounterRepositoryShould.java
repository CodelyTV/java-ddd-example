package tv.codely.mooc.courses_counter.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.domain.CourseIdMother;
import tv.codely.mooc.courses_counter.CoursesCounterModuleInfrastructureTestCase;
import tv.codely.mooc.courses_counter.domain.CoursesCounter;
import tv.codely.mooc.courses_counter.domain.CoursesCounterId;
import tv.codely.mooc.courses_counter.domain.CoursesCounterTotal;
import tv.codely.shared.domain.UuidMother;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

final class MySqlCoursesCounterRepositoryShould extends CoursesCounterModuleInfrastructureTestCase {
    @Test
    void return_an_existing_courses_counter() {
        CoursesCounter counter = new CoursesCounter(
            new CoursesCounterId(UuidMother.random()),
            new CoursesCounterTotal(3),
            Arrays.asList(CourseIdMother.random(), CourseIdMother.random(), CourseIdMother.random())
        );

        repository.save(counter);

        assertEquals(Optional.of(counter), repository.search());
    }
}
