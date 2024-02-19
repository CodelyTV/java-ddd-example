package tv.codely.mooc.students.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentEmail;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentName;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryStudentRepositoryTest {

    @Test
    void save_a_student() {
        InMemoryStudentRepository repository = new InMemoryStudentRepository();
        Student student = new Student(
                new StudentId(UUID.randomUUID().toString()),
                new StudentName("name"),
                new StudentEmail("email@sad.com")
        );

        repository.save(student);
    }

    @Test
    void return_an_existing_student() {
        InMemoryStudentRepository repository = new InMemoryStudentRepository();
        Student student = new Student(
                new StudentId(UUID.randomUUID().toString()),
                new StudentName("name"),
                new StudentEmail("as@d.com"));

        repository.save(student);

        assertEquals(Optional.of(student), repository.search(student.id()));
    }

    @Test
    void not_return_a_non_existing_student() {
        InMemoryStudentRepository repository = new InMemoryStudentRepository();

        assertFalse(repository.search(new StudentId(
                UUID.randomUUID().toString()
        )).isPresent());
    }
}
