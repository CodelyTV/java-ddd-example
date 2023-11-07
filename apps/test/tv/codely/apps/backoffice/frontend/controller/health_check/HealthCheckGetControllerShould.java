package tv.codely.apps.backoffice.frontend.controller.health_check;

import org.junit.jupiter.api.Test;

import tv.codely.apps.ApplicationTestCase;

final class HealthCheckGetControllerShould extends ApplicationTestCase {

	@Test
	void check_the_app_is_working_ok() throws Exception {
		assertResponse("/health-check", 200, "{'application':'backoffice_frontend','status':'ok'}");
	}
}
