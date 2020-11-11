package tv.codely.mooc.students.application.register;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tv.codely.mooc.students.StudentsModuleUnitTestCase;
import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentMother;

final class StudentRegistrarTestShould extends StudentsModuleUnitTestCase {
    StudentRegistrar registrar;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        registrar = new StudentRegistrar(repository);
    }

    @Test
    void register_a_valid_student() {
        RegisterStudentRequest request = RegisterStudentRequestMother.random();
        Student                student = StudentMother.fromRequest(request);

        registrar.register(request);

        shouldHaveSaved(student);
    }
}
