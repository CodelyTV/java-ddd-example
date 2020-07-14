package tv.codely.shared.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class EmailValueObject {
    private final String value;

    public EmailValueObject(String value) {
        ensureValidEmail(value);
        this.value = value;
    }

    public String value() {
        return value;
    }

    private void ensureValidEmail(String value) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";

        Pattern emailPattern = Pattern.compile(emailRegex);
        boolean emailMatchSuccess = emailPattern.matcher(value).matches();
        if(!emailMatchSuccess) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailValueObject that = (EmailValueObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
