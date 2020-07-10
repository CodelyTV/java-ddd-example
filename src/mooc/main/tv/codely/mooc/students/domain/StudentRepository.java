package tv.codely.mooc.students.domain;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    List<Student> searchAll();
    Optional<Student> search(String id);
    void save(Student student);
}
