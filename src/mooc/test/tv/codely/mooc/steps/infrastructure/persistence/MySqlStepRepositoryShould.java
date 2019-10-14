package tv.codely.mooc.steps.infrastructure.persistence;

import org.junit.jupiter.api.Test;
import tv.codely.mooc.courses.domain.Course;
import tv.codely.mooc.courses.domain.CourseIdMother;
import tv.codely.mooc.courses.domain.CourseMother;
import tv.codely.mooc.steps.StepsModuleInfrastructureTestCase;
import tv.codely.mooc.steps.domain.StepIdMother;
import tv.codely.mooc.steps.domain.challenge.ChallengeStep;
import tv.codely.mooc.steps.domain.challenge.ChallengeStepMother;
import tv.codely.mooc.steps.domain.video.VideoStep;
import tv.codely.mooc.steps.domain.video.VideoStepMother;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

final class MySqlStepRepositoryShould extends StepsModuleInfrastructureTestCase {
    @Test
    void save_a_video_step() {
        VideoStep step = VideoStepMother.random();

        repository.save(step);
    }

    @Test
    void save_a_challenge_step() {
        ChallengeStep step = ChallengeStepMother.random();

        repository.save(step);
    }

    @Test
    void return_an_existing_challenge_step() {
        ChallengeStep step = ChallengeStepMother.random();

        repository.save(step);

        assertEquals(Optional.of(step), repository.search(step.id()));
    }

    @Test
    void return_an_existing_video_step() {
        VideoStep step = VideoStepMother.random();

        repository.save(step);

        assertEquals(Optional.of(step), repository.search(step.id()));
    }

    @Test
    void not_return_a_non_existing_course() {
        assertFalse(repository.search(StepIdMother.random()).isPresent());
    }
}
