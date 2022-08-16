package tv.codely.apps.backoffice.frontend.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.Serializable;
import java.util.HashMap;

@Controller
public final class HomeGetWebController {
    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("pages/home", new HashMap<String, Serializable>() {{
            put("title", "Welcome");
            put("description", "CodelyTV - Backoffice");
        }});
    }
}
