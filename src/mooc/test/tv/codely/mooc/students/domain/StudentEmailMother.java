package tv.codely.mooc.students.domain;

import tv.codely.shared.domain.EmailMother;

public final class StudentEmailMother {
    public static StudentEmail create(String value) {
        return new StudentEmail(value);
    }

    public static StudentEmail random() {
        return create(EmailMother.random());
    }
}
