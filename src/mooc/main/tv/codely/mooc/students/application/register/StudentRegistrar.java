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

    public void register(
        StudentId id,
        StudentName name,
        StudentSurname surname,
        StudentEmail email
    ) {
        Student student = Student.create(id, name, surname, email);

        repository.register(student);
        eventBus.publish(student.pullDomainEvents());
    }
}
