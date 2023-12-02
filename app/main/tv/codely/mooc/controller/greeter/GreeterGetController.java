package tv.codely.mooc.controller.greeter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public final class GreeterGetController {

    @RequestMapping("/greeter")
    public HashMap<String, String> index(@RequestParam String name) {
        HashMap<String, String> resp = new HashMap<>();
        String greet = "hello " + name;
        resp.put("response", greet);

        return resp;
    }
}
