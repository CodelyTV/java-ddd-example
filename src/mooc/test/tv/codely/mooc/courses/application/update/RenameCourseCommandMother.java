package tv.codely.mooc.courses.application.update;

import tv.codely.mooc.courses.domain.*;

public final class RenameCourseCommandMother {
    public static RenameCourseCommand create(CourseId id, CourseName newName) {
        return new RenameCourseCommand(id.value(), newName.value());
    }

    public static RenameCourseCommand fromCourse(Course course) {
        return create(course.id(), CourseNameMother.random());
    }

    public static RenameCourseCommand random() {
        return create(CourseIdMother.random(), CourseNameMother.random());
    }
}
