package tv.codely.apps.backoffice.backend.controller.courses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import tv.codely.backoffice.courses.application.BackofficeCoursesResponse;
import tv.codely.backoffice.courses.application.search_by_criteria.SearchBackofficeCoursesByCriteriaQuery;
import tv.codely.shared.domain.DomainError;
import tv.codely.shared.domain.bus.command.CommandBus;
import tv.codely.shared.domain.bus.query.QueryBus;
import tv.codely.shared.domain.bus.query.QueryHandlerExecutionError;
import tv.codely.shared.infrastructure.spring.ApiController;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET })
public final class CoursesGetController extends ApiController {

	public CoursesGetController(QueryBus queryBus, CommandBus commandBus) {
		super(queryBus, commandBus);
	}

	@GetMapping("/courses")
	public List<HashMap<String, String>> index(@RequestParam HashMap<String, Serializable> params)
		throws QueryHandlerExecutionError {
		BackofficeCoursesResponse courses = ask(
			new SearchBackofficeCoursesByCriteriaQuery(
				parseFilters(params),
				Optional.ofNullable((String) params.get("order_by")),
				Optional.ofNullable((String) params.get("order")),
				Optional.ofNullable((Integer) params.get("limit")),
				Optional.ofNullable((Integer) params.get("offset"))
			)
		);

		return courses
			.courses()
			.stream()
			.map(response ->
				new HashMap<String, String>() {
					{
						put("id", response.id());
						put("name", response.name());
						put("duration", response.duration());
					}
				}
			)
			.collect(Collectors.toList());
	}

	@Override
	public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
		return null;
	}

	private List<HashMap<String, String>> parseFilters(HashMap<String, Serializable> params) {
		int maxParams = params.size();

		List<HashMap<String, String>> filters = new ArrayList<>();

		for (int possibleFilterKey = 0; possibleFilterKey < maxParams; possibleFilterKey++) {
			if (params.containsKey(String.format("filters[%s][field]", possibleFilterKey))) {
				int key = possibleFilterKey;

				filters.add(
					new HashMap<String, String>() {
						{
							put("field", (String) params.get(String.format("filters[%s][field]", key)));
							put("operator", (String) params.get(String.format("filters[%s][operator]", key)));
							put("value", (String) params.get(String.format("filters[%s][value]", key)));
						}
					}
				);
			}
		}

		return filters;
	}
}
