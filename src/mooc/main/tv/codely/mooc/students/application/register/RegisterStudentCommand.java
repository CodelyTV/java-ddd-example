package tv.codely.mooc.students.application.register;

import tv.codely.shared.domain.bus.command.Command;

public final class RegisterStudentCommand implements Command {
    private final String id;
    private final String name;
    private final String surname;
    private final String email;

    public RegisterStudentCommand(String id, String name, String surname, String email) {
        this.id      = id;
        this.name    = name;
        this.surname = surname;
        this.email   = email;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String surname() {
        return surname;
    }

    public String email() {
        return email;
    }
}
