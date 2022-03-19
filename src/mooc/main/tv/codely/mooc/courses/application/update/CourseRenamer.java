package tv.codely.mooc.courses.application.update;

import tv.codely.mooc.courses.domain.*;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.event.EventBus;

@Service
public final class CourseRenamer {
    private final CourseFinder finder;
    private final CourseRepository repository;
    private final EventBus eventBus;

    public CourseRenamer(CourseRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
        finder = new CourseFinder(repository);
    }

    public void rename(CourseId id, CourseName newName) throws CourseNotExist {
        Course course = finder.find(id).orElseThrow(() -> new CourseNotExist(id));
        course.rename(newName);

        repository.save(course);
        eventBus.publish(course.pullDomainEvents());
    }
}
