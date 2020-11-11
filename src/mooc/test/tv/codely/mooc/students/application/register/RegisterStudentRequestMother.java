package tv.codely.mooc.students.application.register;

import tv.codely.mooc.students.domain.*;

public class RegisterStudentRequestMother {
    public static RegisterStudentRequest create(StudentId id, StudentName name, StudentSurname surname, StudentEmail email) {
        return new RegisterStudentRequest(id.value(), name.value(), surname.value(), email.value());
    }

    public static RegisterStudentRequest random() {
        return create(
            StudentIdMother.random(),
            StudentNameMother.random(),
            StudentSurnameMother.random(),
            StudentEmailMother.random()
        );
    }
}
