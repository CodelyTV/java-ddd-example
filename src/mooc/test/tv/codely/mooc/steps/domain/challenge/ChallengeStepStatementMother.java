package tv.codely.mooc.steps.domain.challenge;

import tv.codely.shared.domain.WordMother;

public final class ChallengeStepStatementMother {
    public static ChallengeStepStatement create(String value) {
        return new ChallengeStepStatement(value);
    }

    public static ChallengeStepStatement random() {
        return create(WordMother.random());
    }
}
