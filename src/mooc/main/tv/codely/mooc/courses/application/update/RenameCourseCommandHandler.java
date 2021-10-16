package tv.codely.mooc.courses.application.update;

import tv.codely.mooc.courses.domain.CourseId;
import tv.codely.mooc.courses.domain.CourseName;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.command.CommandHandler;

@Service
public final class RenameCourseCommandHandler implements CommandHandler<RenameCourseCommand> {
    private final CourseRenamer renamer;

    public RenameCourseCommandHandler(CourseRenamer renamer) {
        this.renamer = renamer;
    }

    @Override
    public void handle(RenameCourseCommand command) {
        CourseId id = new CourseId(command.id());
        CourseName newName = new CourseName(command.newName());

        renamer.rename(id, newName);
    }
}
