package tv.codely.backoffice.auth.domain;

public final class AuthUser {
    private final AuthUsername username;
    private final AuthPassword password;

    public AuthUser(AuthUsername username, AuthPassword password) {
        this.username = username;
        this.password = password;
    }

    public AuthUsername username() {
        return username;
    }

    public boolean passwordMatches(AuthPassword password) {
        return this.password.equals(password);
    }
}
