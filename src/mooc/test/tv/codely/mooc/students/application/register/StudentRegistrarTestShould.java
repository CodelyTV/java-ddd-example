package tv.codely.mooc.students.application.register;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentMother;
import tv.codely.mooc.students.domain.StudentRepository;

import static org.mockito.Mockito.*;

final class StudentRegistrarTestShould {
    @Test
    void register_a_valid_student() {
        StudentRepository repository = mock(StudentRepository.class);
        StudentRegistrar  registrar  = new StudentRegistrar(repository);

        RegisterStudentRequest request = RegisterStudentRequestMother.random();
        Student                student = StudentMother.fromRequest(request);

        registrar.register(request);

        verify(repository, atLeastOnce()).register(student);
    }
}
