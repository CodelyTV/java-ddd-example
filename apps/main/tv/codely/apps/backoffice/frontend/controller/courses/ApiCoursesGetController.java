package tv.codely.apps.backoffice.frontend.controller.courses;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tv.codely.backoffice.courses.application.BackofficeCoursesResponse;
import tv.codely.backoffice.courses.application.search_all.SearchAllBackofficeCoursesQuery;
import tv.codely.shared.domain.bus.query.QueryBus;
import tv.codely.shared.domain.bus.query.QueryNotRegisteredError;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public final class ApiCoursesGetController {
    private final QueryBus bus;

    public ApiCoursesGetController(QueryBus bus) {
        this.bus = bus;
    }

    @GetMapping("/api/courses")
    public List<HashMap<String, String>> index() throws QueryNotRegisteredError {
        BackofficeCoursesResponse courses = bus.ask(new SearchAllBackofficeCoursesQuery());

        return courses.courses().stream().map(response -> new HashMap<String, String>() {{
            put("id", response.id());
            put("name", response.name());
            put("duration", response.duration());
        }}).collect(Collectors.toList());
    }
}
