package tv.codely.mooc.students.application.register;

import tv.codely.mooc.students.domain.*;
import tv.codely.shared.domain.Service;

@Service
public class StudentRegistrar {
    private final StudentRepository repository;

    public StudentRegistrar(StudentRepository repository) {
        this.repository = repository;
    }

    public void register(String id, String name, String surname, String email) {
        Student student = Student.create(new StudentId(id), name, surname, email);
        repository.register(student);
    }
}
