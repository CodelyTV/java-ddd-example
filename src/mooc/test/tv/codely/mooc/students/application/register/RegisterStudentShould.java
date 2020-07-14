package tv.codely.mooc.students.application.register;

import org.junit.jupiter.api.Test;

import tv.codely.mooc.students.domain.*;

import static org.mockito.Mockito.*;

class RegisterStudentShould {

    @Test
    void save_a_valid_student() {
        StudentRepository repository = mock(StudentRepository.class);
        RegisterStudent register = new RegisterStudent(repository);

        RegisterStudentRequest request = new RegisterStudentRequest("1aab45ba-3c7a-4344-8936-78466eca77fa", "Goku", "Son", "songoku@kamehouse.com");

        Student student = new Student(
            new StudentId(request.id()),
            new StudentName(request.name()),
            new StudentSurName(request.surname()),
            new StudentEmail(request.email())
        );

        register.register(request);

        verify(repository, atLeastOnce()).save(student);
    }
}
