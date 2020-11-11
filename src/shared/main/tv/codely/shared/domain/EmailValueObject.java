package tv.codely.shared.domain;

import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;

import java.util.Objects;

public abstract class EmailValueObject {
    private static final EmailValidator emailValidator = new EmailValidator();
    private              String         value;

    public EmailValueObject(String value) {
        ensureValidEmail(value);
        this.value = value;
    }

    public String value() {
        return value;
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
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
