package tv.codely.mooc.courses.domain;

import java.util.Objects;

public final class Course {
    private final CourseId id;
    private final String name;
    private final String duration;

    public Course(CourseId id, String name, String duration) {
        this.id       = id;
        this.name     = name;
        this.duration = duration;
    }

    public CourseId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String duration() {
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
