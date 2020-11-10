package tv.codely.apps.mooc.backend.controller.students;

import org.junit.jupiter.api.Test;
import tv.codely.apps.mooc.MoocApplicationTestCase;

public final class StudentsPutControllerShould extends MoocApplicationTestCase {
    @Test
    void register_a_valid_non_existing_student() throws Exception {
        assertRequestWithBody(
            "PUT",
            "/students/1bab45ba-3c7a-4344-8936-78466eca17fa",
            "{\"name\": \"some-name\", \"surname\": \"some-surname\", \"email\": \"email@email.com\"}",
            201
        );
    }
}
