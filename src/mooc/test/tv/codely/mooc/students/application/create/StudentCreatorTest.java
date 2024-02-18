package tv.codely.mooc.students.application.create;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentRepository;

import static org.mockito.Mockito.*;
public class StudentCreatorTest {

    @Test
    public void create_a_valid_student() {
        StudentRepository repository = mock(StudentRepository.class);
        StudentCreator creator = new StudentCreator(repository);

        String id = "some-id";
        String name = "name";
        String surname = "surname";

        Student student = new Student(id, name, surname);

        creator.create(student.id(), student.name(), student.surname());

        verify(repository, atLeastOnce()).save(student);
    }

}
