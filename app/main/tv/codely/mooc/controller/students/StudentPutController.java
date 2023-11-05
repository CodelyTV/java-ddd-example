package tv.codely.mooc.controller.students;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tv.codely.mooc.controller.courses.Request;

@RestController
public class StudentPutController {

    @PutMapping("/student/{id}")
    public ResponseEntity index(@PathVariable String id, @RequestBody Request request) {

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
