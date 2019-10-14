package tv.codely.mooc.courses_counter;

import org.springframework.beans.factory.annotation.Autowired;
import tv.codely.mooc.MoocContextInfrastructureTestCase;
import tv.codely.mooc.courses_counter.domain.CoursesCounterRepository;

public abstract class CoursesCounterModuleInfrastructureTestCase extends MoocContextInfrastructureTestCase {
    @Autowired
    protected CoursesCounterRepository repository;
}
