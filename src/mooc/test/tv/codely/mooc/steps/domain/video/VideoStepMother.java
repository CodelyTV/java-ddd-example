package tv.codely.mooc.steps.domain.video;

import tv.codely.mooc.steps.domain.StepId;
import tv.codely.mooc.steps.domain.StepIdMother;
import tv.codely.mooc.steps.domain.StepTitle;
import tv.codely.mooc.steps.domain.StepTitleMother;
import tv.codely.shared.domain.VideoUrl;
import tv.codely.shared.domain.VideoUrlMother;

public final class VideoStepMother {
    public static VideoStep create(StepId id, StepTitle title, VideoUrl videoUrl, VideoStepText text) {
        return new VideoStep(id, title, videoUrl, text);
    }

    public static VideoStep random() {
        return create(
            StepIdMother.random(),
            StepTitleMother.random(),
            VideoUrlMother.random(),
            VideoStepTextMother.random()
        );
    }
}
