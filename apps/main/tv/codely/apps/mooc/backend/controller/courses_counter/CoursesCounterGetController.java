package tv.codely.apps.mooc.backend.controller.courses_counter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tv.codely.mooc.courses_counter.application.find.CoursesCounterFinder;
import tv.codely.mooc.courses_counter.application.find.CoursesCounterResponse;

import java.util.HashMap;

@RestController
public final class CoursesCounterGetController {
    CoursesCounterFinder finder;

    public CoursesCounterGetController(CoursesCounterFinder finder) {
        this.finder = finder;
    }

    @GetMapping("/courses-counter")
    public HashMap<String, Integer> index() {
        CoursesCounterResponse response = finder.find();

        return new HashMap<String, Integer>() {{
            put("total", response.total());
        }};
    }
}
