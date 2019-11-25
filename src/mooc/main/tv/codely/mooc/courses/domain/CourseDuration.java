package tv.codely.mooc.courses.domain;

import tv.codely.shared.domain.StringValueObject;

public final class CourseDuration extends StringValueObject {
    public CourseDuration(String value) {
        super(value);
    }

    private CourseDuration() {
        super("");
    }
}
