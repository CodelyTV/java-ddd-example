package tv.codely.mooc.steps.domain;

import tv.codely.shared.domain.WordMother;

public final class StepTitleMother {
    public static StepTitle create(String value) {
        return new StepTitle(value);
    }

    public static StepTitle random() {
        return create(WordMother.random());
    }
}
