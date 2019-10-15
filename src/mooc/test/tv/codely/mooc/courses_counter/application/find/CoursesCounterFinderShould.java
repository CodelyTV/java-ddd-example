package tv.codely.mooc.courses_counter.application.find;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses_counter.CoursesCounterModuleUnitTestCase;
import tv.codely.mooc.courses_counter.domain.CoursesCounter;
import tv.codely.mooc.courses_counter.domain.CoursesCounterMother;
import tv.codely.mooc.courses_counter.domain.CoursesCounterNotInitialized;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class CoursesCounterFinderShould extends CoursesCounterModuleUnitTestCase {
    CoursesCounterFinder finder;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        finder = new CoursesCounterFinder(repository);
    }

    @Test
    void it_should_find_an_existing_courses_counter() {
        CoursesCounter         counter  = CoursesCounterMother.random();
        CoursesCounterResponse response = CoursesCounterResponseMother.create(counter.total().value());

        shouldSearch(counter);

        assertEquals(response, finder.find());
    }

    @Test
    void it_should_throw_an_exception_when_courses_counter_does_not_exists() {
        shouldSearch();

        assertThrows(CoursesCounterNotInitialized.class, () -> finder.find());
    }
}
