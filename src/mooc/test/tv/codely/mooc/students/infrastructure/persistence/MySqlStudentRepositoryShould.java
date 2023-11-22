package tv.codely.mooc.students.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.students.StudentsModuleInfrastructureTestCase;
import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentMother;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
class MySqlStudentRepositoryShould extends StudentsModuleInfrastructureTestCase {
    @Test
    void register_a_student() {
        Student student = StudentMother.random();

        mySqlStudentRepository.register(student);
    }

    @Test
    void return_all_existing_students() {
        Student student1 = StudentMother.random();
        Student student2 = StudentMother.random();

        mySqlStudentRepository.register(student1);
        mySqlStudentRepository.register(student2);

        List<Student> students = mySqlStudentRepository.searchAll();
        assertEquals(2, students.size());
        assertEquals(student1, students.get(0));
        assertEquals(student2, students.get(1));
    }

    @Test
    void not_return_non_existing_students() {
        assertTrue(mySqlStudentRepository.searchAll().isEmpty());
    }
}
