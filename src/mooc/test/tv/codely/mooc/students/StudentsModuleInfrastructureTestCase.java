package tv.codely.mooc.students;

import org.springframework.beans.factory.annotation.Autowired;
import tv.codely.mooc.MoocContextInfrastructureTestCase;
import tv.codely.mooc.students.domain.StudentRepository;

public abstract class StudentsModuleInfrastructureTestCase extends MoocContextInfrastructureTestCase {
    @Autowired
    protected StudentRepository mySqlStudentRepository;
}
