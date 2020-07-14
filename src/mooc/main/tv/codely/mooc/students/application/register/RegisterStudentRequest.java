package tv.codely.mooc.students.application.register;

import java.util.Objects;

public final class RegisterStudentRequest {
    private final String    id;
    private final String    name;
    private final String    surname;
    private final String    email;

    public RegisterStudentRequest(String id, String name, String surname, String email) {
        this.id      = id;
        this.name    = name;
        this.surname = surname;
        this.email   = email;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String surname() {
        return surname;
    }

    public String email() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterStudentRequest that = (RegisterStudentRequest) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(surname, that.surname) &&
            Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email);
    }
}
