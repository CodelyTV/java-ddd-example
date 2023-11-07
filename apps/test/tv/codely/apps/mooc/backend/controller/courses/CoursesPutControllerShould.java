package tv.codely.apps.mooc.backend.controller.courses;

import org.junit.jupiter.api.Test;

import tv.codely.apps.mooc.MoocApplicationTestCase;

public final class CoursesPutControllerShould extends MoocApplicationTestCase {

	@Test
	void create_a_valid_non_existing_course() throws Exception {
		assertRequestWithBody(
			"PUT",
			"/courses/1aab45ba-3c7a-4344-8936-78466eca77fa",
			"{\"name\": \"The best course\", \"duration\": \"5 hours\"}",
			201
		);
	}
}
