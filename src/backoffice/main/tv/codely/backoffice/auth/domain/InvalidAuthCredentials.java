package tv.codely.backoffice.auth.domain;

public final class InvalidAuthCredentials extends RuntimeException {
    public InvalidAuthCredentials(AuthUsername username) {
        super(String.format("The credentials for <%s> are invalid", username.value()));
    }
}
