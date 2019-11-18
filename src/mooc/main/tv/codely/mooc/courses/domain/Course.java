package tv.codely.mooc.courses.domain;

import tv.codely.shared.domain.AggregateRoot;
import tv.codely.shared.domain.course.CourseCreatedDomainEvent;

import java.util.Objects;

public final class Course extends AggregateRoot {
    private final CourseId       id;
    private final CourseName     name;
    private final CourseDuration duration;

    public Course(CourseId id, CourseName name, CourseDuration duration) {
        this.id       = id;
        this.name     = name;
        this.duration = duration;
    }

    private Course() {
        id       = null;
        name     = null;
        duration = null;
    }

    public static Course create(CourseId id, CourseName name, CourseDuration duration) {
        Course course = new Course(id, name, duration);

        course.record(new CourseCreatedDomainEvent(id.value(), name.value(), duration.value()));

        return course;
    }

    public CourseId id() {
        return id;
    }

    public CourseName name() {
        return name;
    }

    public CourseDuration duration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        return id.equals(course.id) &&
               name.equals(course.name) &&
               duration.equals(course.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration);
    }
}
