package tv.codely.mooc.students.infrastructure;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentIdMother;
import tv.codely.shared.domain.UuidGenerator;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class InMemoryStudentRepositoryShould {
    private  UuidGenerator uuidGenerator;
    @Test
    void save_a_valid_student() {
        uuidGenerator = mock(UuidGenerator.class);

        InMemoryStudentRepository repository = new InMemoryStudentRepository(uuidGenerator);

        Student student = new Student(StudentIdMother.random(), "Goku", "Son", "songoku@kamehouse.com");

        repository.save(student);
    }

    @Test
    void search_an_existing_student() {
        uuidGenerator = mock(UuidGenerator.class);

        InMemoryStudentRepository repository = new InMemoryStudentRepository(uuidGenerator);

        Student student = new Student(StudentIdMother.random(), "Goku", "Son", "songoku@kamehouse.com");

        repository.save(student);

        Assert.assertEquals(Optional.of(student), repository.search(student.id().value()));
    }

    @Test
    void not_find_a_non_existing_student() {
        uuidGenerator = mock(UuidGenerator.class);

        InMemoryStudentRepository repository = new InMemoryStudentRepository(uuidGenerator);

        Assert.assertFalse(repository.search(UUID.randomUUID().toString()).isPresent());
    }

}
