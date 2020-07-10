package tv.codely.mooc.students.infrastructure;

import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentRepository;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.UuidGenerator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public final class InMemoryStudentRepository implements StudentRepository {
    private UuidGenerator generator;
    private HashMap<String, Student> students = new HashMap<>();

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

    @Override
    public Optional<Student> search(String id) {
        return Optional.ofNullable(students.get(id));
    }

    @Override
    public void save(Student student) {
        this.students.put(student.id().value(), student);
    }
}
