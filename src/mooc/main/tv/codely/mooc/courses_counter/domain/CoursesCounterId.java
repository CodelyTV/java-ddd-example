package tv.codely.mooc.courses_counter.domain;

import tv.codely.shared.domain.Identifier;

public final class CoursesCounterId extends Identifier {
    public CoursesCounterId(String value) {
        super(value);
    }

    private CoursesCounterId() {
    }
}
