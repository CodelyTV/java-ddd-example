package tv.codely.mooc.courses.application.find;

import tv.codely.mooc.courses.application.CourseResponse;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseNotExist;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.mooc.courses.domain.service.DomainCourseFinder;
import tv.codely.shared.domain.Service;

@Service
public final class CourseFinder {
    private final DomainCourseFinder domainCourseFinder;

    public CourseFinder(CourseRepository repository) {
        this.domainCourseFinder = new DomainCourseFinder(repository);
    }

    public CourseResponse find(CourseId id) throws CourseNotExist {
        return CourseResponse.fromAggregate(domainCourseFinder.find(id));
    }
}
