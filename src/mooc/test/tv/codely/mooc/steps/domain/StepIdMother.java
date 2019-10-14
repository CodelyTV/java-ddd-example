package tv.codely.mooc.steps.domain;

import tv.codely.shared.domain.UuidMother;

public final class StepIdMother {
    public static StepId create(String value) {
        return new StepId(value);
    }

    public static StepId random() {
        return create(UuidMother.random());
    }
}
