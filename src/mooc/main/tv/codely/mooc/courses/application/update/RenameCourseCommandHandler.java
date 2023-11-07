package tv.codely.mooc.courses.application.update;

import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseName;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.command.CommandHandler;

@Service
public final class RenameCourseCommandHandler implements CommandHandler<RenameCourseCommand> {
    private final CourseNameUpdater courseNameUpdater;

    public RenameCourseCommandHandler(final CourseNameUpdater courseNameUpdater) {
        this.courseNameUpdater = courseNameUpdater;
    }

    @Override
    public void handle(final RenameCourseCommand command) {
        CourseId       id       = new CourseId(command.id());
        CourseName     name     = new CourseName(command.name());

        courseNameUpdater.renameCourse(id, name);
    }
}
