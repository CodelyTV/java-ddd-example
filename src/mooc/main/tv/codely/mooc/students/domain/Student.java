package tv.codely.mooc.students.domain;

public class Student {
    private StudentId id;
    private StudentName name;
    private StudentEmail email;

    public Student(StudentId id, StudentName name, StudentEmail email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public StudentId id() {
        return id;
    }

    public StudentName name() {
        return name;
    }

    public StudentEmail email() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;

        Student student = (Student) o;

        return id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
