package tv.codely.apps.mooc.backend.controller.health_check;

import org.junit.jupiter.api.Test;
import tv.codely.apps.mooc.backend.controller.RequestTestCase;

final class HealthCheckGetControllerShould extends RequestTestCase {
    @Test
    void check_the_app_is_working_ok() throws Exception {
        this.assertResponse("/health-check", 200, "{'status': 'ok'}");
    }
}
