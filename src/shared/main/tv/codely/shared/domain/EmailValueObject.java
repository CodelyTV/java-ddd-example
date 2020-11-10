package tv.codely.shared.domain;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;

import java.util.Objects;

public abstract class EmailValueObject {
    private static final EmailValidator emailValidator = new EmailValidator();
    private final        String         email;

    public EmailValueObject(String email) {
        ensureValidEmail(email);
        this.email = email;
    }

    public String value() {
        return email;
    }

    private void ensureValidEmail(String value) {
        if (!emailValidator.isValid(value, null)) {
            throw new InvalidEmail(value);
        }
    }

    @Override
    public String toString() {
        return this.value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmailValueObject)) {
            return false;
        }
        EmailValueObject that = (EmailValueObject) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
