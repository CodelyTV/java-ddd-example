package tv.codely.mooc.students.domain;

import java.util.Objects;

public final class Student {
    private final StudentId id;
    private final StudentName name;
    private final StudentSurName surname;
    private final StudentEmail email;

    public Student(StudentId id, StudentName name, StudentSurName surname, StudentEmail email) {
        this.id      = id;
        this.name    = name;
        this.surname = surname;
        this.email   = email;
    }

    public StudentId id() {
        return id;
    }

    public StudentName name() { return name; }

    public StudentSurName surname() { return surname; }

    public StudentEmail email() { return email; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
            Objects.equals(name, student.name) &&
            Objects.equals(surname, student.surname) &&
            Objects.equals(email, student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email);
    }
}
