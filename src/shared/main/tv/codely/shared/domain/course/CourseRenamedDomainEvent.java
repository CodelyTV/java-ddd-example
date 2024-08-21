package tv.codely.shared.domain.course;

import tv.codely.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class CourseRenamedDomainEvent extends DomainEvent {
	private final String name;

	public CourseRenamedDomainEvent() {
		super(null);

		this.name = null;
	}

	public CourseRenamedDomainEvent(String aggregateId, String name) {
		super(aggregateId);

		this.name = name;
	}

	public CourseRenamedDomainEvent(
		String aggregateId,
		String eventId,
		String occurredOn,
		String name
	) {
		super(aggregateId, eventId, occurredOn);

		this.name = name;
	}

	@Override
	public String eventName() {
		return "course.renamed";
	}

	@Override
	public HashMap<String, Serializable> toPrimitives() {
		return new HashMap<>() {{
			put("name", name);
		}};
	}

	@Override
	public CourseRenamedDomainEvent fromPrimitives(
		String aggregateId,
		HashMap<String, Serializable> body,
		String eventId,
		String occurredOn
	) {
		return new CourseRenamedDomainEvent(
			aggregateId,
			eventId,
			occurredOn,
			(String) body.get("name")
		);
	}

	public String name() {
		return name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CourseRenamedDomainEvent that = (CourseRenamedDomainEvent) o;
		return Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
