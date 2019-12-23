package tv.codely.mooc.courses.application.find;

import tv.codely.mooc.courses.application.CourseResponse;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseNotExist;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.Service;

@Service
public final class CourseFinder {
    private final CourseRepository repository;

    public CourseFinder(CourseRepository repository) {
        this.repository = repository;
    }

    public CourseResponse find(CourseId id) throws CourseNotExist {
        return repository.search(id)
                         .map(CourseResponse::fromAggregate)
                         .orElseThrow(() -> new CourseNotExist(id));
    }
}
