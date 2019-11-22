package tv.codely.mooc.students.domain;

public final class Student {
    private final StudentId id;
    private final String    name;
    private final String    surname;
    private final String    email;

    public Student(StudentId id, String name, String surname, String email) {
        this.id      = id;
        this.name    = name;
        this.surname = surname;
        this.email   = email;
    }

    public StudentId id() {
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
}
