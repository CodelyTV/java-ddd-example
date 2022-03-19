package tv.codely.mooc.courses.application;

import tv.codely.shared.domain.bus.query.Response;

import java.util.List;
import java.util.Objects;

public final class CoursesResponse implements Response {
    private final List<CourseResponse> courses;

    public CoursesResponse(List<CourseResponse> courses) {
        this.courses = courses;
    }

    public List<CourseResponse> courses() {
        return courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoursesResponse response = (CoursesResponse) o;
        return Objects.equals(courses, response.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courses);
    }
}
