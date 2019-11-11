package tv.codely.apps.backoffice.frontend.controller.courses;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;
import java.util.HashMap;

@Controller
public final class CoursesGetWebController {
    @GetMapping("/courses")
    public ModelAndView index() {
        return new ModelAndView("pages/courses", new HashMap<String, Serializable>() {{
            put("title", "Courses");
            put("description", "Courses CodelyTV - Backoffice");
            put("courses_counter", 5);
        }});
    }
}
