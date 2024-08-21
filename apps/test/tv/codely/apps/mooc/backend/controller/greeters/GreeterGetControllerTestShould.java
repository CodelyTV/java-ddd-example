package tv.codely.apps.mooc.backend.controller.greeters;

import org.junit.jupiter.api.Test;
import tv.codely.apps.mooc.MoocApplicationTestCase;

final class GreeterGetControllerTestShould extends MoocApplicationTestCase {
    @Test
    void greet_a_user() throws Exception {
        assertResponse("/greet/octavi", 200, "{'greet':'Hi, Octavi!'}");
    }
}
