package tv.codely.mooc.students.domain;

import tv.codely.shared.domain.AggregateRoot;
import tv.codely.shared.domain.student.StudentRegisteredDomainEvent;

import java.util.Objects;

public final class Student extends AggregateRoot {
    private final StudentId      id;
    private final StudentName    name;
    private final StudentSurname surname;
    private final StudentEmail   email;

    public Student(StudentId id, StudentName name, StudentSurname surname, StudentEmail email) {
        this.id      = id;
        this.name    = name;
        this.surname = surname;
        this.email   = email;
    }

    private Student() {
        id      = null;
        name    = null;
        surname = null;
        email   = null;
    }

    public static Student create(StudentId id, StudentName name, StudentSurname surname, StudentEmail email) {
        Student student = new Student(id, name, surname, email);

        student.record(new StudentRegisteredDomainEvent(id.value(), name.value(), surname.value(), email.value()));

        return student;
    }

    public StudentId id() {
        return id;
    }

    public StudentName name() {
        return name;
    }

    public StudentSurname surname() {
        return surname;
    }

    public StudentEmail email() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id.equals(student.id) &&
            name.equals(student.name) &&
            surname.equals(student.surname) &&
            email.equals(student.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email);
    }
}
