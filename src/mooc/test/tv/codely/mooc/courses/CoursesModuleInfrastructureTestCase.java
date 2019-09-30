package tv.codely.mooc.courses;

import org.springframework.beans.factory.annotation.Autowired;
import tv.codely.shared.infrastructure.InfrastructureTestCase;
import tv.codely.mooc.courses.infrastructure.persistence.InMemoryCourseRepository;

public abstract class CoursesModuleInfrastructureTestCase extends InfrastructureTestCase {
    @Autowired
    protected InMemoryCourseRepository repository;
}
