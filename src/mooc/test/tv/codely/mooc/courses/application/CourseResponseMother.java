package tv.codely.mooc.courses.application;

import tv.codely.mooc.courses.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public final class CourseResponseMother {
    public static CourseResponse create(CourseId id, CourseName name, CourseDuration duration) {
        return new CourseResponse(id.value(), name.value(), duration.value());
    }

    public static CourseResponse create(Course course) {
        return create(course.id(), course.name(), course.duration());
    }

    public static List<CourseResponse> create(List<Course> courses) {
        return courses.stream().map(CourseResponseMother::create).collect(Collectors.toList());
    }

    public static CourseResponse random() {
        return create(CourseIdMother.random(), CourseNameMother.random(), CourseDurationMother.random());
    }
}
