package tv.codely.apps.mooc.backend.controller.courses;

import org.junit.jupiter.api.Test;
import tv.codely.apps.mooc.MoocApplicationTestCase;

final class CourseGetControllerShould extends MoocApplicationTestCase {
    @Test
    void find_an_existing_course() throws Exception {
        String id   = "99ad55f5-6eab-4d73-b383-c63268e251e8";
        String body = "{\"name\": \"The best course\", \"duration\": \"5 hours\"}";

        givenThereIsACourse(id, body);

        assertResponse(String.format("/courses/%s", id), 200, body);
    }

    private void givenThereIsACourse(String id, String body) throws Exception {
        assertRequestWithBody("PUT", String.format("/courses/%s", id), body, 201);
    }
}
