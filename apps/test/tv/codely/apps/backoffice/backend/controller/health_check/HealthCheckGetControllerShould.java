package tv.codely.apps.backoffice.backend.controller.health_check;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import tv.codely.apps.ApplicationTestCase;

final class HealthCheckGetControllerShould extends ApplicationTestCase {
    @Test
    void check_the_app_is_working_ok_with_valid_credentials() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("javi", "barbitas");

        assertResponse("/health-check", 200, "{'application':'backoffice_backend','status':'ok'}", headers);
    }

    @Test
    void not_check_the_app_is_working_ok_with_invalid_credentials() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("tipo_de_incognito", "homer.sampson");

        assertResponse("/health-check", 403, "", headers);
    }

    @Test
    void not_check_the_app_is_working_ok_with_invalid_credentials_of_an_existing_user() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("rafa", "incorrect.password");

        assertResponse("/health-check", 403, "", headers);
    }

    @Test
    void not_check_the_app_is_working_ok_with_no_credentials() throws Exception {
        assertResponse("/health-check", 401, "");
    }
}
