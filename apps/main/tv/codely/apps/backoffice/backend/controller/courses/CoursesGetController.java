package tv.codely.apps.backoffice.backend.controller.courses;

import org.springframework.web.bind.annotation.*;
import tv.codely.backoffice.courses.application.BackofficeCoursesResponse;
import tv.codely.backoffice.courses.application.search_by_criteria.SearchBackofficeCoursesByCriteriaQuery;
import tv.codely.shared.domain.bus.query.QueryBus;
import tv.codely.shared.domain.bus.query.QueryHandlerExecutionError;
import tv.codely.shared.domain.bus.query.QueryNotRegisteredError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public final class CoursesGetController {
    private final QueryBus bus;

    public CoursesGetController(QueryBus bus) {
        this.bus = bus;
    }

    @GetMapping("/courses")
    public List<HashMap<String, String>> index(
        @RequestParam HashMap<String, Serializable> params
    ) throws QueryNotRegisteredError, QueryHandlerExecutionError {
        BackofficeCoursesResponse courses = bus.ask(
            new SearchBackofficeCoursesByCriteriaQuery(
                parseFilters(params),
                Optional.ofNullable((String) params.get("order_by")),
                Optional.ofNullable((String) params.get("order")),
                Optional.ofNullable((Integer) params.get("limit")),
                Optional.ofNullable((Integer) params.get("offset"))
            )
        );

        return courses.courses().stream().map(response -> new HashMap<String, String>() {{
            put("id", response.id());
            put("name", response.name());
            put("duration", response.duration());
        }}).collect(Collectors.toList());
    }

    private List<HashMap<String, String>> parseFilters(HashMap<String, Serializable> params) {
        int maxParams = params.size();

        List<HashMap<String, String>> filters = new ArrayList<>();

        for (int possibleFilterKey = 0; possibleFilterKey < maxParams; possibleFilterKey++) {
            if (params.containsKey(String.format("filters[%s][field]", possibleFilterKey))) {
                int key = possibleFilterKey;

                filters.add(new HashMap<String, String>() {{
                    put("field", (String) params.get(String.format("filters[%s][field]", key)));
                    put("operator", (String) params.get(String.format("filters[%s][operator]", key)));
                    put("value", (String) params.get(String.format("filters[%s][value]", key)));
                }});
            }
        }

        return filters;
    }
}
