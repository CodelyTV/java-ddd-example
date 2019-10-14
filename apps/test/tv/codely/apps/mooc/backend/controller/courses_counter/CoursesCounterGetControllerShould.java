package tv.codely.apps.mooc.backend.controller.courses_counter;

import org.junit.jupiter.api.Test;
import tv.codely.apps.mooc.backend.controller.ApplicationTestCase;
import tv.codely.mooc.courses.domain.CourseCreatedDomainEvent;

public final class CoursesCounterGetControllerShould extends ApplicationTestCase {
    @Test
    void get_the_counter_with_one_course() throws Exception {
        givenISendAnEventToTheBus(
            new CourseCreatedDomainEvent("8f34bc99-e0e2-4296-a008-75f51f03aeb4", "DDD en Java", "7 days")
        );

        assertResponse("/courses-counter", 200, "{'total': 1}");
    }
}
