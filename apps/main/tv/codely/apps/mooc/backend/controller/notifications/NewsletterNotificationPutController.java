package tv.codely.apps.mooc.backend.controller.notifications;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import tv.codely.mooc.notifications.application.send_new_courses_newsletter.SendNewCoursesNewsletterCommand;
import tv.codely.shared.domain.bus.command.CommandBus;
import tv.codely.shared.domain.bus.command.CommandHandlerExecutionError;
import tv.codely.shared.domain.bus.command.CommandNotRegisteredError;

@RestController
public final class NewsletterNotificationPutController {
    private final CommandBus bus;

    public NewsletterNotificationPutController(CommandBus bus) {
        this.bus = bus;
    }

    @PutMapping(value = "/newsletter/{id}")
    public ResponseEntity<String> index(
        @PathVariable String id
    ) throws CommandNotRegisteredError, CommandHandlerExecutionError {
        bus.dispatch(new SendNewCoursesNewsletterCommand(id));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
