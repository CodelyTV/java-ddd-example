package tv.codely.backoffice.auth.domain;

import tv.codely.shared.domain.WordMother;

public final class AuthUsernameMother {
    public static AuthUsername create(String value) {
        return new AuthUsername(value);
    }

    public static AuthUsername random() {
        return create(WordMother.random());
    }
}
