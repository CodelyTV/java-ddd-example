package tv.codely.shared.domain;

public abstract class EmailValueObject {
    private final String value;

    public EmailValueObject(String value) {
        ensureIsValidEmail(value);
        this.value = value;
    }

    private void ensureIsValidEmail(String value) {
        if (!value.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            throw new IllegalArgumentException(String.format("The email <%s> is not valid", value));
        }
    }

    public String value() {
        return value;
    }
}
