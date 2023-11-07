package tv.codely.apps.mooc.backend.controller.notifications;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import tv.codely.mooc.notifications.application.send_new_courses_newsletter.SendNewCoursesNewsletterCommand;
import tv.codely.shared.domain.DomainError;
import tv.codely.shared.domain.bus.command.CommandBus;
import tv.codely.shared.domain.bus.command.CommandHandlerExecutionError;
import tv.codely.shared.domain.bus.query.QueryBus;
import tv.codely.shared.infrastructure.spring.ApiController;

@RestController
public final class NewsletterNotificationPutController extends ApiController {

	public NewsletterNotificationPutController(QueryBus queryBus, CommandBus commandBus) {
		super(queryBus, commandBus);
	}

	@PutMapping(value = "/newsletter/{id}")
	public ResponseEntity<String> index(@PathVariable String id) throws CommandHandlerExecutionError {
		dispatch(new SendNewCoursesNewsletterCommand(id));

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@Override
	public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
		return null;
	}
}
