package tv.codely.apps.mooc.backend.controller.students;

import org.junit.jupiter.api.Test;
import tv.codely.apps.mooc.MoocApplicationTestCase;

import static org.junit.jupiter.api.Assertions.*;

class StudentPostControllerShould extends MoocApplicationTestCase {
    @Test
    void create_a_valid_non_existing_student() throws Exception {
        assertRequestWithBody(
            "POST",
            "/students/1aab45ba-3c7a-4344-8936-78466eca77fa",
            "{\"name\": \"Goku\", \"surname\": \"Son\", \"email\": \"songoku@kamehouse.com\"}",
            201
        );
    }
}
