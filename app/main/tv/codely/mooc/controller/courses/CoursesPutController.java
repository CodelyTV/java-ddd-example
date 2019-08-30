package tv.codely.mooc.controller.courses;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class CoursesPutController {
    @PutMapping("/courses")
    public String index() {
        return "All is working ok!";
    }
}
