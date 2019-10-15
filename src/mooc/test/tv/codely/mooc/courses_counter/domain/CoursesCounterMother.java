package tv.codely.mooc.courses_counter.domain;

import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseIdMother;
import tv.codely.shared.domain.ListMother;

import java.util.ArrayList;
import java.util.List;

public final class CoursesCounterMother {
    public static CoursesCounter create(
        CoursesCounterId id,
        CoursesCounterTotal total,
        List<CourseId> existingCourses
    ) {
        return new CoursesCounter(id, total, existingCourses);
    }

    public static CoursesCounter withOne(CourseId courseId) {
        return create(CoursesCounterIdMother.random(), CoursesCounterTotalMother.one(), ListMother.one(courseId));
    }

    public static CoursesCounter random() {
        List<CourseId> existingCourses = ListMother.random(CourseIdMother::random);

        return create(
            CoursesCounterIdMother.random(),
            CoursesCounterTotalMother.create(existingCourses.size()),
            existingCourses
        );
    }

    public static CoursesCounter incrementing(CoursesCounter existingCounter, CourseId courseId) {
        List<CourseId> existingCourses = new ArrayList<>(existingCounter.existingCourses());
        existingCourses.add(courseId);

        return create(
            existingCounter.id(),
            CoursesCounterTotalMother.create(existingCounter.total().value() + 1),
            existingCourses
        );
    }
}
