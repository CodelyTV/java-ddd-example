package tv.codely.apps.mooc.backend.controller.greeters;

import org.junit.jupiter.api.Test;
import tv.codely.apps.mooc.MoocApplicationTestCase;

public class GreeterGetControllerShould extends MoocApplicationTestCase {
    @Test
    void check_greeter_is_send_greets() throws Exception {
        String name = "Ivan";
        String expectedResponse = String.format("{\"greet\":\"Hi %s\"}", name);
        assertResponse(String.format("/greets/%s", name), 200, expectedResponse);
    }
}
