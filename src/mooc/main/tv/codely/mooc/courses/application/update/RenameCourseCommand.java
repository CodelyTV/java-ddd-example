package tv.codely.mooc.courses.application.update;

import tv.codely.shared.domain.bus.command.Command;

public final class RenameCourseCommand implements Command {
    private final String id;
    private final String newName;

    public RenameCourseCommand(String id, String newName) {
        this.id = id;
        this.newName = newName;
    }

    public String id() {
        return id;
    }

    public String newName() {
        return newName;
    }
}
