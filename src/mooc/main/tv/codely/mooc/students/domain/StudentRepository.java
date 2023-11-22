package tv.codely.mooc.students.domain;

import java.util.List;

public interface StudentRepository {
    void register(Student student);
    List<Student> searchAll();
}
