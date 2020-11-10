package tv.codely.shared.domain;

public final class InvalidEmail extends DomainError {
    public InvalidEmail(String email) {
        super("invalid_email", String.format("The email <%s> is invalid", email));
    }
}
