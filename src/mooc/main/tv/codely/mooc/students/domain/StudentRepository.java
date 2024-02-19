package tv.codely.mooc.students.domain;

import java.util.Optional;

public interface StudentRepository {
    void save(Student student);

    Optional<Student> search(StudentId id);
}
