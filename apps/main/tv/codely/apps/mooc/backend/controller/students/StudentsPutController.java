package tv.codely.apps.mooc.backend.controller.students;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tv.codely.mooc.students.application.register.RegisterStudentRequest;
import tv.codely.mooc.students.application.register.StudentRegistrar;

@RestController
public final class StudentsPutController {

    private final StudentRegistrar registrar;

    public StudentsPutController(StudentRegistrar registrar) {
        this.registrar = registrar;
    }

    @PutMapping(value = "/students/{id}")
    public ResponseEntity<String> index(@PathVariable String id, @RequestBody Request request) {
        registrar.register(new RegisterStudentRequest(id, request.name(), request.surname(), request.email()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

final class Request {
    private String name;
    private String surname;
    private String email;

    String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String surname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String email() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
