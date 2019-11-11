package tv.codely.apps.backoffice.frontend.controller.courses;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import tv.codely.mooc.courses_counter.application.find.CoursesCounterResponse;
import tv.codely.mooc.courses_counter.application.find.FindCoursesCounterQuery;
import tv.codely.shared.domain.bus.query.QueryBus;
import tv.codely.shared.domain.bus.query.QueryNotRegisteredError;

import java.io.Serializable;
import java.util.HashMap;

@Controller
public final class CoursesGetWebController {
    private final QueryBus bus;

    public CoursesGetWebController(QueryBus bus) {
        this.bus = bus;
    }

    @GetMapping("/courses")
    public ModelAndView index() throws QueryNotRegisteredError {
        CoursesCounterResponse counterResponse = bus.ask(new FindCoursesCounterQuery());

        return new ModelAndView("pages/courses", new HashMap<String, Serializable>() {{
            put("title", "Courses");
            put("description", "Courses CodelyTV - Backoffice");
            put("courses_counter", counterResponse.total());
        }});
    }
}
