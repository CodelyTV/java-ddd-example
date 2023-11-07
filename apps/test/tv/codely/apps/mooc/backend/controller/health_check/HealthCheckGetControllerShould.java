package tv.codely.apps.mooc.backend.controller.health_check;

import org.junit.jupiter.api.Test;

import tv.codely.apps.mooc.MoocApplicationTestCase;

final class HealthCheckGetControllerShould extends MoocApplicationTestCase {

	@Test
	void check_the_app_is_working_ok() throws Exception {
		assertResponse("/health-check", 200, "{'application':'mooc_backend','status':'ok'}");
	}
}
