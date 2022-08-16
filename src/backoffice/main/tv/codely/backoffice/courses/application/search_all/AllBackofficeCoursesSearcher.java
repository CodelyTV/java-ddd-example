package tv.codely.backoffice.courses.application.search_all;

import tv.codely.backoffice.courses.application.BackofficeCourseResponse;
import tv.codely.backoffice.courses.application.BackofficeCoursesResponse;
import tv.codely.backoffice.courses.domain.BackofficeCourseRepository;
import tv.codely.shared.domain.Service;

import java.util.stream.Collectors;

@Service
public final class AllBackofficeCoursesSearcher {
    private final BackofficeCourseRepository repository;

    public AllBackofficeCoursesSearcher(BackofficeCourseRepository repository) {
        this.repository = repository;
    }

    public BackofficeCoursesResponse search() {
        return new BackofficeCoursesResponse(
            repository.searchAll().stream().map(BackofficeCourseResponse::fromAggregate).collect(Collectors.toList())
        );
    }
}
