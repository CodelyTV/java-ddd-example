package tv.codely.mooc.courses;

import org.springframework.beans.factory.annotation.Autowired;
import tv.codely.mooc.courses.domain.CourseRepository;
import tv.codely.mooc.courses.infrastructure.persistence.InMemoryCourseRepository;
import tv.codely.shared.infrastructure.InfrastructureTestCase;

public abstract class CoursesModuleInfrastructureTestCase extends InfrastructureTestCase {
    protected InMemoryCourseRepository inMemoryCourseRepository = new InMemoryCourseRepository();
    @Autowired
    protected CourseRepository         mySqlCourseRepository;
}
