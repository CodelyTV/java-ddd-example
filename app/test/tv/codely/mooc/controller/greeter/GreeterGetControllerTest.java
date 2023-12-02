package tv.codely.mooc.controller.greeter;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.controller.RequestTestCase;

public final class GreeterGetControllerTest extends RequestTestCase {
    @Test
    public void it_should_get_a_greeter_response() throws Exception {
        this.getRequest("/greeter?name=pepe", 200, "{'response': 'hello pepe'}");
    }
}
