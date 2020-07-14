package tv.codely.mooc.students.application.register;

import tv.codely.mooc.students.domain.*;
import tv.codely.shared.domain.Service;

@Service
public class RegisterStudent {
    private final StudentRepository repository;

    public RegisterStudent(StudentRepository repository) {
        this.repository = repository;
    }

    public void register(RegisterStudentRequest request){
        Student student = new Student(
            new StudentId(request.id()),
            new StudentName(request.name()),
            new StudentSurName(request.surname()),
            new StudentEmail(request.email())
        );

        repository.save(student);
    }
}
