package tv.codely.apps.mooc.backend.controller.greeter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tv.codely.shared.domain.DomainError;
import tv.codely.shared.domain.bus.command.CommandBus;
import tv.codely.shared.domain.bus.query.QueryBus;
import tv.codely.shared.infrastructure.spring.ApiController;

import java.util.HashMap;

@RestController
public final class GreeterGetController extends ApiController {
    public GreeterGetController(
        QueryBus queryBus,
        CommandBus commandBus
    ) {
        super(queryBus, commandBus);
    }

    @GetMapping("/greeter")
    public HashMap<String, String> index(@RequestParam(name = "name", required = false) String name) {
        HashMap<String, String> status = new HashMap<>();
        status.put("message", getGreetingMessage(name));
        return status;
    }

    private String getGreetingMessage(String name) {
        if (name == null) {
            name = "Unknown";
        }
        return "Hello " + name;
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}
