package tv.codely.mooc.students.application.register;

import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentRepository;
import tv.codely.shared.domain.Service;

@Service
public class StudentRegistrar {
    private final StudentRepository repository;

    public StudentRegistrar(StudentRepository repository) {
        this.repository = repository;
    }

    public void register(RegisterStudentRequest request) {
        Student student = Student.create(new StudentId(request.id()), request.name(), request.surname(), request.email());
        repository.register(student);
    }
}
