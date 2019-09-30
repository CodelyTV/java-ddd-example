package tv.codely.mooc.courses.domain;

import java.util.Objects;

public final class Course {
    private final CourseId       id;
    private final CourseName     name;
    private final CourseDuration duration;

    public Course(CourseId id, CourseName name, CourseDuration duration) {
        this.id       = id;
        this.name     = name;
        this.duration = duration;
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
