package tv.codely.backoffice.courses.application;

import tv.codely.backoffice.courses.domain.BackofficeCourse;
import tv.codely.shared.domain.bus.query.Response;

public final class BackofficeCourseResponse implements Response {
    private final String id;
    private final String name;
    private final String duration;


    public BackofficeCourseResponse(String id, String name, String duration) {
        this.id       = id;
        this.name     = name;
        this.duration = duration;
    }

    public static BackofficeCourseResponse fromAggregate(BackofficeCourse course) {
        return new BackofficeCourseResponse(course.id(), course.name(), course.duration());
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
