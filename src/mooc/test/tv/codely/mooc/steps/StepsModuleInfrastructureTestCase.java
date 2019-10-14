package tv.codely.mooc.steps;

import org.springframework.beans.factory.annotation.Autowired;
import tv.codely.mooc.MoocContextInfrastructureTestCase;
import tv.codely.mooc.steps.domain.StepRepository;

public abstract class StepsModuleInfrastructureTestCase extends MoocContextInfrastructureTestCase {
    @Autowired
    protected StepRepository repository;
}
