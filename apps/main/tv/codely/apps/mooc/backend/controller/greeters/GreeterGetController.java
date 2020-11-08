package tv.codely.apps.mooc.backend.controller.greeters;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/greet/{name}")
    public HashMap<String, String> index(@PathVariable String name) {
        HashMap<String, String> greeting = new HashMap<>();
        greeting.put("greet", String.format("Hi, %s!", StringUtils.capitalize(name)));

        return greeting;
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}
