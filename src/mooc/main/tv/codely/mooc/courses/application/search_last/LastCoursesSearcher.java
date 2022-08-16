package tv.codely.mooc.courses.application.search_last;

import tv.codely.mooc.courses.application.CourseResponse;
import tv.codely.mooc.courses.application.CoursesResponse;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.criteria.Criteria;
import tv.codely.shared.domain.criteria.Filters;
import tv.codely.shared.domain.criteria.Order;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public final class LastCoursesSearcher {
    private final CourseRepository repository;

    public LastCoursesSearcher(CourseRepository repository) {
        this.repository = repository;
    }

    public CoursesResponse search(int courses) {
        Criteria criteria = new Criteria(
            Filters.none(),
            Order.none(),
            Optional.of(courses),
            Optional.empty()
        );

        return new CoursesResponse(
            repository.matching(criteria).stream().map(CourseResponse::fromAggregate).collect(Collectors.toList())
        );
    }
}
