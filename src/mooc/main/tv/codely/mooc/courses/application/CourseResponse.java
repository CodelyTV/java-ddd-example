package tv.codely.mooc.courses.application;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.shared.domain.bus.query.Response;

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
}
