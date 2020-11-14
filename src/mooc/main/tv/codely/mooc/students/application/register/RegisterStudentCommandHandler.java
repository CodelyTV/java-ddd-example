package tv.codely.mooc.students.application.register;

import tv.codely.mooc.students.domain.StudentEmail;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentName;
import tv.codely.mooc.students.domain.StudentSurname;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.command.CommandHandler;

@Service
public final class RegisterStudentCommandHandler implements CommandHandler<RegisterStudentCommand> {
    private final StudentRegistrar registrar;

    public RegisterStudentCommandHandler(StudentRegistrar registrar) {
        this.registrar = registrar;
    }

    @Override
    public void handle(RegisterStudentCommand command) {
        StudentId      id      = new StudentId(command.id());
        StudentName    name    = new StudentName(command.name());
        StudentSurname surname = new StudentSurname(command.surname());
        StudentEmail   email   = new StudentEmail(command.email());

        registrar.register(id, name, surname, email);
    }
}
