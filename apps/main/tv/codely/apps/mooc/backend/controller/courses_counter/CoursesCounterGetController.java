package tv.codely.apps.mooc.backend.controller.courses_counter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tv.codely.mooc.courses_counter.application.find.CoursesCounterResponse;
import tv.codely.mooc.courses_counter.application.find.FindCoursesCounterQuery;
import tv.codely.shared.domain.bus.query.QueryBus;
import tv.codely.shared.domain.bus.query.QueryHandlerExecutionError;
import tv.codely.shared.domain.bus.query.QueryNotRegisteredError;

import java.util.HashMap;

@RestController
public final class CoursesCounterGetController {
    QueryBus bus;

    public CoursesCounterGetController(QueryBus bus) {
        this.bus = bus;
    }

    @GetMapping("/courses-counter")
    public HashMap<String, Integer> index() throws QueryNotRegisteredError, QueryHandlerExecutionError {
        CoursesCounterResponse response = bus.ask(new FindCoursesCounterQuery());

        return new HashMap<String, Integer>() {{
            put("total", response.total());
        }};
    }
}
