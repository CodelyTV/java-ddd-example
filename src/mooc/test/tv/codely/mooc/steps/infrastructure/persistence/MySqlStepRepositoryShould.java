package tv.codely.mooc.steps.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseIdMother;
import tv.codely.mooc.courses.domain.CourseMother;
import tv.codely.mooc.steps.StepsModuleInfrastructureTestCase;
import tv.codely.mooc.steps.domain.Step;
import tv.codely.mooc.steps.domain.StepIdMother;
import tv.codely.mooc.steps.domain.challenge.ChallengeStep;
import tv.codely.mooc.steps.domain.challenge.ChallengeStepMother;
import tv.codely.mooc.steps.domain.video.VideoStep;
import tv.codely.mooc.steps.domain.video.VideoStepMother;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

final class MySqlStepRepositoryShould extends StepsModuleInfrastructureTestCase {
    @Test
    void save_a_step() {
        for (Step step : steps()) {
            repository.save(step);
        }
    }

    @Test
    void return_an_existing_step() {
        for (Step step : steps()) {
            repository.save(step);

            assertEquals(Optional.of(step), repository.search(step.id()));
        }
    }

    @Test
    void not_return_a_non_existing_course() {
        assertFalse(repository.search(StepIdMother.random()).isPresent());
    }

    private List<? extends Step> steps() {
        return Arrays.asList(ChallengeStepMother.random(), VideoStepMother.random());
    }
}
