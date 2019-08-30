package tv.codely.mooc.controller.courses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class CoursesPutController {
    @PutMapping(value = "/courses/{id}")
    public ResponseEntity index(@PathVariable String id, @RequestBody Request request) {
        return new ResponseEntity(HttpStatus.CREATED);
    }
}

final class Request {
    private String name;
    private String duration;

    public String name() {
        return name;
    }

    public String duration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setName(String name) {
        this.name = name;
    }
}
