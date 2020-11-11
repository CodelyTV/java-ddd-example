package tv.codely.mooc.students.infrastructure.persistence;

import tv.codely.mooc.students.domain.*;
import tv.codely.shared.domain.UuidGenerator;

import java.util.Arrays;
import java.util.List;

public final class InMemoryStudentRepository implements StudentRepository {
    private UuidGenerator generator;

    public InMemoryStudentRepository(UuidGenerator generator) {
        this.generator = generator;
    }

    @Override
    public List<Student> searchAll() {
        return Arrays.asList(
            new Student(
                new StudentId(generator.generate()),
                new StudentName("name"),
                new StudentSurname("surname"),
                new StudentEmail("email@mail.com")
            ),
            new Student(
                new StudentId(generator.generate()),
                new StudentName("Other name"),
                new StudentSurname("Other surname"),
                new StudentEmail("another@mail.com")
            )
        );
    }

    @Override
    public void register(Student student) {
    }
}
