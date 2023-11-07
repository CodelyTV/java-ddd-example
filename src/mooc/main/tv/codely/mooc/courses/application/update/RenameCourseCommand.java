package tv.codely.mooc.courses.application.update;

import tv.codely.shared.domain.bus.command.Command;

public class RenameCourseCommand implements Command {
    private final String id;

    public RenameCourseCommand(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private final String name;

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }
}
