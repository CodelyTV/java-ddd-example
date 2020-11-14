package tv.codely.mooc.students.application.register;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.students.StudentsModuleUnitTestCase;
import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentMother;
import tv.codely.mooc.students.domain.StudentRegisteredDomainEventMother;
import tv.codely.shared.domain.student.StudentRegisteredDomainEvent;

final class RegisterStudentCommandHandlerShould extends StudentsModuleUnitTestCase {
    private RegisterStudentCommandHandler handler;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        handler = new RegisterStudentCommandHandler(new StudentRegistrar(repository, eventBus));
    }

    @Test
    void register_a_valid_student() {
        RegisterStudentCommand       command     = RegisterStudentCommandMother.random();
        Student                      student     = StudentMother.fromCommand(command);
        StudentRegisteredDomainEvent domainEvent = StudentRegisteredDomainEventMother.fromStudent(student);

        handler.handle(command);

        shouldHaveSaved(student);
        shouldHavePublished(domainEvent);
    }
}
