package tv.codely.mooc.students.application;

import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentIdMother;
import tv.codely.shared.domain.EmailMother;
import tv.codely.shared.domain.WordMother;

public final class StudentResponseMother {
    public static StudentResponse create(StudentId id, String name, String surname, String email) {
        return new StudentResponse(id.value(), name, surname, email);
    }

    public static StudentResponse random() {
        return create(StudentIdMother.random(), WordMother.random(), WordMother.random(), EmailMother.random());
    }
}
