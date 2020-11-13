package tv.codely.mooc.students.domain;

import tv.codely.shared.domain.student.StudentRegisteredDomainEvent;

public final class StudentRegisteredDomainEventMother {
    public static StudentRegisteredDomainEvent create(
        StudentId id, StudentName name, StudentSurname surname, StudentEmail email
    ) {
        return new StudentRegisteredDomainEvent(id.value(), name.value(), surname.value(), email.value());
    }

    public static StudentRegisteredDomainEvent fromStudent(Student student) {
        return create(student.id(), student.name(), student.surname(), student.email());
    }

    public static StudentRegisteredDomainEvent random() {
        return create(
            StudentIdMother.random(),
            StudentNameMother.random(),
            StudentSurnameMother.random(),
            StudentEmailMother.random()
        );
    }
}
