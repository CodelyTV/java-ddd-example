package tv.codely.apps.mooc.controller.students;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tv.codely.mooc.students.application.create.StudentCreator;
import tv.codely.mooc.students.application.create.StudentCreatorRequest;

@RestController
public class StudentsPutController {

    private final StudentCreator creator;

    public StudentsPutController(StudentCreator creator) {
        this.creator = creator;
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<?> create(@PathVariable String id, @RequestBody Request request) {
        StudentCreatorRequest studentCreatorRequest = new StudentCreatorRequest(id, request.name(), request.email());

        creator.create(studentCreatorRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
