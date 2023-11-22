package tv.codely.mooc.students;

import org.junit.jupiter.api.BeforeEach;
import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentRepository;
import tv.codely.shared.infrastructure.UnitTestCase;

import static org.mockito.Mockito.*;

public abstract class StudentsModuleUnitTestCase extends UnitTestCase {
    protected StudentRepository repository;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        repository = mock(StudentRepository.class);
    }

    public void shouldHaveSaved(Student student) {
        verify(repository, atLeastOnce()).register(student);
    }
}
