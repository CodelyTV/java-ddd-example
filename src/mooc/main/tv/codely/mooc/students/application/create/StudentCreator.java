package tv.codely.mooc.students.application.create;

import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.students.domain.*;
import tv.codely.shared.domain.Service;

@Service
public class StudentCreator {
    private final StudentRepository repository;

    public StudentCreator(StudentRepository repository) {
        this.repository = repository;
    }

    public void create(StudentCreatorRequest request) {
        StudentId id = new StudentId(request.id());
        StudentName name = new StudentName(request.name());
        StudentEmail email = new StudentEmail(request.email());

        repository.save(new Student(id, name, email));
    }
}
