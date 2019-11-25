package tv.codely.backoffice.courses.application.search_by_criteria;

import tv.codely.backoffice.courses.application.BackofficeCourseResponse;
import tv.codely.backoffice.courses.application.BackofficeCoursesResponse;
import tv.codely.backoffice.courses.domain.BackofficeCourseRepository;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.criteria.Criteria;
import tv.codely.shared.domain.criteria.Filters;
import tv.codely.shared.domain.criteria.Order;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public final class BackofficeCoursesByCriteriaSearcher {
    private final BackofficeCourseRepository repository;

    public BackofficeCoursesByCriteriaSearcher(BackofficeCourseRepository repository) {
        this.repository = repository;
    }

    public BackofficeCoursesResponse search(
        Filters filters,
        Order order,
        Optional<Integer> limit,
        Optional<Integer> offset
    ) {
        Criteria criteria = new Criteria(filters, order, limit, offset);

        return new BackofficeCoursesResponse(
            repository.matching(criteria)
                      .stream()
                      .map(BackofficeCourseResponse::fromAggregate)
                      .collect(Collectors.toList())
        );
    }
}
