package tv.codely.mooc.students.application.register;

import tv.codely.mooc.students.domain.*;
import tv.codely.shared.domain.Service;

@Service
public class StudentRegistrar {
    private final StudentRepository repository;

    public StudentRegistrar(StudentRepository repository) {
        this.repository = repository;
    }

    public void register(RegisterStudentRequest request) {
        Student student = Student.create(
            new StudentId(request.id()),
            new StudentName(request.name()),
            new StudentSurname(request.surname()),
            new StudentEmail(request.email())
        );
        repository.register(student);
    }
}
