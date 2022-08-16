package tv.codely.mooc.steps.domain.video;

import tv.codely.mooc.steps.domain.Step;
import tv.codely.mooc.steps.domain.StepId;
import tv.codely.mooc.steps.domain.StepTitle;
import tv.codely.shared.domain.VideoUrl;

public final class VideoStep extends Step {
    private final VideoUrl      videoUrl;
    private final VideoStepText text;

    public VideoStep(StepId id, StepTitle title, VideoUrl videoUrl, VideoStepText text) {
        super(id, title);

        this.videoUrl = videoUrl;
        this.text     = text;
    }

    private VideoStep() {
        super(null, null);

        this.videoUrl = null;
        this.text     = null;
    }
}
