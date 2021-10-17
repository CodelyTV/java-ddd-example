package tv.codely.mooc.courses.application.find;

import tv.codely.mooc.courses.application.CourseResponse;
import tv.codely.mooc.courses.application.CoursesResponse;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.Service;

import java.util.stream.Collectors;

@Service
public final class AllCoursesFinder {
    private final CourseRepository repository;

    public AllCoursesFinder(CourseRepository repository) {
        this.repository = repository;
    }

    public CoursesResponse findAll() {
        return new CoursesResponse(
            repository.findAll()
                .stream()
                .map(CourseResponse::fromAggregate)
                .collect(Collectors.toList()));
    }
}