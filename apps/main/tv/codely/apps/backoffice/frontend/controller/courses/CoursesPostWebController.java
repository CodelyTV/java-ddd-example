package tv.codely.apps.backoffice.frontend.controller.courses;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import tv.codely.mooc.courses.application.create.CreateCourseCommand;
import tv.codely.shared.domain.bus.command.CommandBus;

import java.util.Map;

@Controller
public final class CoursesPostWebController {
    private final CommandBus bus;

    public CoursesPostWebController(CommandBus bus) {
        this.bus = bus;
    }

    @PostMapping(value = "/courses", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView index(@RequestParam Map<String, String> request) throws Exception {
        bus.dispatch(new CreateCourseCommand(request.get("id"), request.get("name"), request.get("duration")));

        return new RedirectView("/courses");
    }
}
