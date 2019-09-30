package tv.codely.mooc.courses.application.create;

import tv.codely.mooc.courses.domain.*;

public final class CreateCourseRequestMother {
    public static CreateCourseRequest create(CourseId id, CourseName name, CourseDuration duration) {
        return new CreateCourseRequest(id.value(), name.value(), duration.value());
    }

    public static CreateCourseRequest random() {
        return create(CourseIdMother.random(), CourseNameMother.random(), CourseDurationMother.random());
    }
}
