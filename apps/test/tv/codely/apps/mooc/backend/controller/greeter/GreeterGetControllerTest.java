package tv.codely.apps.mooc.backend.controller.greeter;

import org.junit.jupiter.api.Test;
import tv.codely.apps.mooc.MoocApplicationTestCase;

class GreeterGetControllerTest extends MoocApplicationTestCase {

    @Test
    void givenNameWhenResponseThenReturnGreeter() throws Exception {
        assertResponse("/greeter?name=Alejandro", 200, "{'Hello': 'Alejandro'}");
    }
}
