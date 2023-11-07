package tv.codely.mooc.courses.application.find;

import tv.codely.mooc.courses.application.CourseResponse;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseNotExist;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.shared.domain.Service;

@Service
public final class CourseFinder {
    private final tv.codely.mooc.courses.domain.CourseFinder finder;

    public CourseFinder(CourseRepository repository) {
        this.finder = new tv.codely.mooc.courses.domain.CourseFinder(repository);
    }

    public CourseResponse find(CourseId id) throws CourseNotExist {
        return finder.find(id)
            .map(CourseResponse::fromAggregate)
            .orElseThrow(() -> new CourseNotExist(id));
    }
}
