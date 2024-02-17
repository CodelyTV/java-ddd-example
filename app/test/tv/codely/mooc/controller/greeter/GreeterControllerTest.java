package tv.codely.mooc.controller.greeter;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.controller.RequestTestCase;

final class GreeterControllerTest extends RequestTestCase {

    @Test
    public void shouldSayHello() throws Exception {
        this.getRequest("/greeter?name=Sergi", 200, "{'message': 'Hello Sergi from the greeter controller!'}");
    }
}
