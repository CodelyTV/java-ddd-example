package tv.codely.mooc.students.domain;

import tv.codely.mooc.courses.domain.CourseName;
import tv.codely.shared.domain.WordMother;

public final class StudentNameMother {
    public static StudentName create(String value) {
        return new StudentName(value);
    }

    public static StudentName random() {
        return create(WordMother.random());
    }
}
