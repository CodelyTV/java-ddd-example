package tv.codely.mooc.controller.courses;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.controller.RequestTestCase;

public class CoursesPutControllerShould extends RequestTestCase {

    @Test
    void create_a_valid_course() throws Exception {
        this.assertRequestWithBody("PUT",
            "/courses/1aab45ba-3c7a-4344-8936-78466eca77fa",
            "{\"name\": \"The best course\", \"duration\": \"5 hours\"}",
            201
            );
    }
}
