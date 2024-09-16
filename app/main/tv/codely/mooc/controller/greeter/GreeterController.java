package tv.codely.mooc.controller.greeter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GreeterController {

    @GetMapping("/greeter")
    public Map<String,String> sayHello(@RequestParam String name) {
        Map<String, String> message = new HashMap<>();
        message.put("message", "Hello " + name + " from the greeter controller!");

        return message;
    }
}
