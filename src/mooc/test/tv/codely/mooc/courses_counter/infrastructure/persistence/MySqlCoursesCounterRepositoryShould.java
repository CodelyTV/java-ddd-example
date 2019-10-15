package tv.codely.mooc.courses_counter.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses_counter.CoursesCounterModuleInfrastructureTestCase;
import tv.codely.mooc.courses_counter.domain.CoursesCounter;
import tv.codely.mooc.courses_counter.domain.CoursesCounterMother;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@Transactional
class MySqlCoursesCounterRepositoryShould extends CoursesCounterModuleInfrastructureTestCase {
    @Test
    void return_an_existing_courses_counter() {
        CoursesCounter counter = CoursesCounterMother.random();

        repository.save(counter);

        assertEquals(Optional.of(counter), repository.search());
    }
}
