package tv.codely.mooc.courses.domain;

import tv.codely.shared.domain.course.CourseCreatedDomainEvent;

public final class CourseCreatedDomainEventMother {
    public static CourseCreatedDomainEvent create(CourseId id, CourseName name, CourseDuration duration) {
        return new CourseCreatedDomainEvent(id.value(), name.value(), duration.value());
    }

    public static CourseCreatedDomainEvent fromCourse(Course course) {
        return create(course.id(), course.name(), course.duration());
    }

    public static CourseCreatedDomainEvent random() {
        return create(CourseIdMother.random(), CourseNameMother.random(), CourseDurationMother.random());
    }
}
