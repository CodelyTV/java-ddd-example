package tv.codely.mooc.courses.application.create;

import tv.codely.mooc.courses.domain.*;
import tv.codely.shared.domain.Service;

@Service
public final class CourseCreator {
    private CourseRepository repository;

    public CourseCreator(CourseRepository repository) {
        this.repository = repository;
    }

    public void create(CreateCourseRequest request) {
        CourseId       id       = new CourseId(request.id());
        CourseName     name     = new CourseName(request.name());
        CourseDuration duration = new CourseDuration(request.duration());

        Course course = new Course(id, name, duration);

        this.repository.save(course);
    }
}
