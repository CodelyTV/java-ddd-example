package tv.codely.mooc.students.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.students.domain.Student;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryStudentRepositoryTest {

    @Test
    void save_a_student() {
        InMemoryStudentRepository repository = new InMemoryStudentRepository();
        Student student = new Student("id", "name", "surname");

        repository.save(student);
    }

    @Test
    void return_an_existing_student() {
        InMemoryStudentRepository repository = new InMemoryStudentRepository();
        Student student = new Student("id", "name", "surname");

        repository.save(student);

        assertEquals(Optional.of(student), repository.search(student.id()));
    }

    @Test
    void not_return_a_non_existing_student() {
        InMemoryStudentRepository repository = new InMemoryStudentRepository();

        assertFalse(repository.search("randomId").isPresent());
    }
}
