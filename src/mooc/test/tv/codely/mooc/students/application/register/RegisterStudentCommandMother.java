package tv.codely.mooc.students.application.register;

import tv.codely.mooc.students.domain.*;

public class RegisterStudentCommandMother {
    public static RegisterStudentCommand create(StudentId id, StudentName name, StudentSurname surname, StudentEmail email) {
        return new RegisterStudentCommand(id.value(), name.value(), surname.value(), email.value());
    }

    public static RegisterStudentCommand random() {
        return create(
            StudentIdMother.random(),
            StudentNameMother.random(),
            StudentSurnameMother.random(),
            StudentEmailMother.random()
        );
    }
}
