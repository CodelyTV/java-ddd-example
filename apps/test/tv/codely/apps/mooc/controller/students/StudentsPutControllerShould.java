package tv.codely.apps.mooc.controller.students;

import org.junit.jupiter.api.Test;
import tv.codely.apps.mooc.controller.RequestTestCase;

public class StudentsPutControllerShould extends RequestTestCase {

    @Test
    public void create_a_valid_non_existing_student() throws Exception {
        assertRequestWithBody(
            "PUT",
            "/students/1aab45ba-3c7a-4344-8936-78466eca77fa",
            "{\"name\": \"The best student\", \"email\": \"example@example.com\"}",
            201);
    }

}
