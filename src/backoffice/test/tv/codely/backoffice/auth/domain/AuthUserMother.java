package tv.codely.backoffice.auth.domain;

import tv.codely.backoffice.auth.application.authenticate.AuthenticateUserCommand;

public final class AuthUserMother {
    public static AuthUser create(AuthUsername username, AuthPassword password) {
        return new AuthUser(username, password);
    }

    public static AuthUser random() {
        return create(AuthUsernameMother.random(), AuthPasswordMother.random());
    }

    public static AuthUser fromCommand(AuthenticateUserCommand command) {
        return create(AuthUsernameMother.create(command.username()), AuthPasswordMother.create(command.password()));
    }

    public static AuthUser withUsername(String username) {
        return create(AuthUsernameMother.create(username), AuthPasswordMother.random());
    }
}
