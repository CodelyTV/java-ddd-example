package tv.codely.mooc.courses.application.find;

import tv.codely.mooc.courses.application.CourseResponse;
import tv.codely.shared.domain.bus.query.Query;

public final class FindCourseQuery implements Query<CourseResponse> {
    private final String id;

    public FindCourseQuery(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }
}
