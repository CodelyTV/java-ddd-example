package tv.codely.apps.mooc.backend.controller.playground;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tv.codely.shared.domain.Utils;

import java.io.Serializable;
import java.util.HashMap;

@RestController
record DomainEventPostController(RabbitTemplate rabbitTemplate) {
	@PostMapping(value = "/domain-events")
	public ResponseEntity<String> index(@RequestBody Request request) {
		System.out.println(request.eventName());

		var serializedEvent = Utils.jsonEncode(request.eventRaw());

		Message message = new Message(
			serializedEvent.getBytes(),
			MessagePropertiesBuilder.newInstance().setContentEncoding("utf-8").setContentType("application/json").build()
		);

		rabbitTemplate.send("domain_events", request.eventName(), message);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}

final class Request {

	private String eventName;
	private Object eventRaw;

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public void setEventRaw(Object eventRaw) {
		this.eventRaw = eventRaw;
	}

	String eventName() {
		return eventName;
	}

	Object eventRaw() {
		return eventRaw;
	}
}
