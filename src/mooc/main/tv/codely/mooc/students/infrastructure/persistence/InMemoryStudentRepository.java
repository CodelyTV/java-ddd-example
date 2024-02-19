package tv.codely.mooc.students.infrastructure.persistence;

import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentRepository;
import tv.codely.shared.domain.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class InMemoryStudentRepository implements StudentRepository {
    private final HashMap<String, Student> students = new HashMap<>();

    public void save(Student student) {
        students.put(student.id().value(), student);
    }

    @Override
    public Optional<Student> search(StudentId id) {
        return Optional.ofNullable(students.get(id.value()));
    }
}
