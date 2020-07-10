package tv.codely.mooc.students.application.create;

import tv.codely.mooc.students.domain.Student;
import tv.codely.mooc.students.domain.StudentId;
import tv.codely.mooc.students.domain.StudentRepository;

public class StudentCreator {
    private final StudentRepository repository;

    public StudentCreator(StudentRepository repository) {
        this.repository = repository;
    }

    public void create(String id, String name, String surName, String email){
        Student student = new Student(new StudentId(id), name, surName, email);

        repository.save(student);
    }
}
