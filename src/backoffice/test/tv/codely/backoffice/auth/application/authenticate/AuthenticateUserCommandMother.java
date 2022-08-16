package tv.codely.backoffice.auth.application.authenticate;

import tv.codely.backoffice.auth.domain.AuthPassword;
import tv.codely.backoffice.auth.domain.AuthPasswordMother;
import tv.codely.backoffice.auth.domain.AuthUsername;
import tv.codely.backoffice.auth.domain.AuthUsernameMother;

public final class AuthenticateUserCommandMother {
    public static AuthenticateUserCommand create(AuthUsername username, AuthPassword password) {
        return new AuthenticateUserCommand(username.value(), password.value());
    }

    public static AuthenticateUserCommand random() {
        return create(AuthUsernameMother.random(), AuthPasswordMother.random());
    }
}
