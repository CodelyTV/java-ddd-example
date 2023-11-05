package tv.codely.mooc.controller.students;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.controller.RequestTestCase;

public class StudentPutControllerShould extends RequestTestCase {

    @Test
    public void should_create_a_new_student() throws Exception{
        this.assertRequestWithBody(
            "PUT",
            "/student/1",
            "{ \"name\": \"pepe\", \"lastname\": \"Gutierrez\" }",
            201
        );
    }
}
