package tv.codely.mooc.courses.application.update;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseName;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.mooc.courses.domain.service.DomainCourseFinder;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.event.EventBus;

@Service
public class CourseNameUpdater {
    private final CourseRepository repository;
    private final DomainCourseFinder domainCourseFinder;
    private final EventBus eventBus;

    public CourseNameUpdater(CourseRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
        this.domainCourseFinder = new DomainCourseFinder(this.repository);
    }

    public void renameCourse(final CourseId courseId, final CourseName newCourseName) {
        final Course course = domainCourseFinder.find(courseId);

        this.repository.save(buildNewCourse(course, newCourseName));

        this.eventBus.publish(course.pullDomainEvents());
    }

    private Course buildNewCourse(final Course course, final CourseName newCourseName) {
        return Course.rename(course.id(), newCourseName, course.duration());
    }
}
