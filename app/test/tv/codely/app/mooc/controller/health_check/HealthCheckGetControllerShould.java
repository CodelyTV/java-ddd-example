package tv.codely.app.mooc.controller.health_check;

import org.junit.jupiter.api.Test;
import tv.codely.app.mooc.controller.RequestTestCase;

final class HealthCheckGetControllerShould extends RequestTestCase {
    @Test
    public void check_the_app_is_working_ok() throws Exception {
        this.assertResponse("/health-check", 200, "{'status': 'ok'}");
    }
}
