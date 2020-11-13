package tv.codely.mooc.students.application.register;

import tv.codely.mooc.students.domain.*;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.event.EventBus;

@Service
public class StudentRegistrar {
    private final StudentRepository repository;
    private final EventBus          eventBus;

    public StudentRegistrar(StudentRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus   = eventBus;
    }

    public void register(RegisterStudentRequest request) {
        Student student = Student.create(
            new StudentId(request.id()),
            new StudentName(request.name()),
            new StudentSurname(request.surname()),
            new StudentEmail(request.email())
        );
        repository.register(student);
        eventBus.publish(student.pullDomainEvents());
    }
}
