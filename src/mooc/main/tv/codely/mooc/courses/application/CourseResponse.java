package tv.codely.mooc.courses.application;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.shared.domain.bus.query.Response;

import java.util.Objects;

public final class CourseResponse implements Response {
    private final String id;
    private final String name;
    private final String duration;

    public CourseResponse(String id, String name, String duration) {
        this.id       = id;
        this.name     = name;
        this.duration = duration;
    }

    public static CourseResponse fromAggregate(Course course) {
        return new CourseResponse(course.id().value(), course.name().value(), course.duration().value());
    }

    public String id() {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseResponse that = (CourseResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, duration);
    }
}
