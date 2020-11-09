package tv.codely.mooc.students.application.register;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentRepository;
import tv.codely.shared.domain.UuidMother;

import static org.mockito.Mockito.*;

final class StudentRegistrarTestShould {
    @Test
    void register_a_valid_student() {
        StudentRepository repository = mock(StudentRepository.class);
        StudentRegistrar  registrar  = new StudentRegistrar(repository);

        StudentId id      = new StudentId(UuidMother.random());
        String    name    = "name";
        String    surname = "surname";
        String    email   = "email";

        RegisterStudentRequest request = new RegisterStudentRequest(id.value(), name, surname, email);

        Student student = new Student(id, name, surname, email);

        registrar.register(request);

        verify(repository, atLeastOnce()).register(student);
    }
}
