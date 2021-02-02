package tv.codely.apps.mooc.backend.controller.greeter;

import org.junit.jupiter.api.Test;
import tv.codely.apps.mooc.MoocApplicationTestCase;

final class GreeterGetControllerShould extends MoocApplicationTestCase {
    @Test
    void greetNameMarcos() throws Exception {
        assertResponse("/greeter?name=Marcos", 200, "{'message':'Hello Marcos'}");
    }

    @Test
    void greetNameAnonymous() throws Exception {
        assertResponse("/greeter", 200, "{'message':'Hello Unknown'}");
    }
}
