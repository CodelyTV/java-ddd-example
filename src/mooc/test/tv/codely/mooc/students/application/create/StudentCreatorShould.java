package tv.codely.mooc.students.application.create;

import org.junit.jupiter.api.Test;

import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentCreatorShould {

    @Test
    void save_a_valid_student() {
        StudentRepository repository = mock(StudentRepository.class);

        StudentCreator creator = new StudentCreator(repository);

        Student student =
            new Student(new StudentId("1aab45ba-3c7a-4344-8936-78466eca77fa"), "Goku", "Son", "songoku@kamehouse.com");

        creator.create("1aab45ba-3c7a-4344-8936-78466eca77fa", "Goku", "Son", "songoku@kamehouse.com");

        verify(repository, atLeastOnce()).save(student);
    }
}
