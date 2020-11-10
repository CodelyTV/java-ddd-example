package tv.codely.mooc.students.application.register;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.students.domain.*;
import tv.codely.shared.domain.UuidMother;

import static org.mockito.Mockito.*;

final class StudentRegistrarTestShould {
    @Test
    void register_a_valid_student() {
        StudentRepository repository = mock(StudentRepository.class);
        StudentRegistrar  registrar  = new StudentRegistrar(repository);

        StudentId      id      = new StudentId(UuidMother.random());
        StudentName    name    = new StudentName("name");
        StudentSurname surname = new StudentSurname("surname");
        StudentEmail   email   = new StudentEmail("email@email.com");

        RegisterStudentRequest request = new RegisterStudentRequest(
            id.value(), name.value(), surname.value(), email.value()
        );

        Student student = new Student(id, name, surname, email);

        registrar.register(request);

        verify(repository, atLeastOnce()).register(student);
    }
}
