package tv.codely.apps.mooc.backend.controller.greeter;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreeterGetController {

    @GetMapping("/greeter")
    public Map<String, String> greeter(@RequestParam String name) {
        return Map.of("Hello", name);
    }

}
