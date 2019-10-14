package tv.codely.mooc.courses.application.create;

import tv.codely.mooc.courses.domain.*;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.event.EventBus;

@Service
public final class CourseCreator {
    private final CourseRepository repository;
    private final EventBus eventBus;

    public CourseCreator(CourseRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus   = eventBus;
    }

    public void create(CreateCourseRequest request) {
        CourseId       id       = new CourseId(request.id());
        CourseName     name     = new CourseName(request.name());
        CourseDuration duration = new CourseDuration(request.duration());

        Course course = Course.create(id, name, duration);

        repository.save(course);
        eventBus.publish(course.pullDomainEvents());
    }
}
