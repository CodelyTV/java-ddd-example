package tv.codely.mooc.students.infrastructure;

import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentRepository;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.UuidGenerator;

import java.util.Arrays;
import java.util.List;

@Service
public final class InMemoryStudentRepository implements StudentRepository {
    private UuidGenerator generator;

    public InMemoryStudentRepository(UuidGenerator generator) {
        this.generator = generator;
    }

    @Override
    public List<Student> searchAll() {
        return Arrays.asList(
            new Student(new StudentId(generator.generate()), "name", "surname", "email@mail.com"),
            new Student(new StudentId(generator.generate()), "Other name", "Other surname", "another@mail.com")
        );
    }
}
