package tv.codely.mooc.courses.domain;

import tv.codely.shared.domain.WordMother;

public final class CourseNameMother {
    public static CourseName create(String value) {
        return new CourseName(value);
    }

    public static CourseName random() {
        return create(WordMother.random());
    }
}
