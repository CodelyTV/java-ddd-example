package tv.codely.mooc.controller.health_check;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.controller.RequestTestCase;

final class HealthCheckGetControllerTest extends RequestTestCase {
    @Test
    public void shouldCheckTheAppIsWorkingOk() throws Exception {
        this.assertResponse("/health-check", 200, "{'status': 'ok'}");
    }
}
