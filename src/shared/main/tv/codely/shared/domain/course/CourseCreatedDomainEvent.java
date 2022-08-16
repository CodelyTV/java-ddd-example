package tv.codely.shared.domain.course;

import tv.codely.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class CourseCreatedDomainEvent extends DomainEvent {
    private final String name;
    private final String duration;

    public CourseCreatedDomainEvent() {
        super(null);

        this.name     = null;
        this.duration = null;
    }

    public CourseCreatedDomainEvent(String aggregateId, String name, String duration) {
        super(aggregateId);

        this.name     = name;
        this.duration = duration;
    }

    public CourseCreatedDomainEvent(
        String aggregateId,
        String eventId,
        String occurredOn,
        String name,
        String duration
    ) {
        super(aggregateId, eventId, occurredOn);

        this.name     = name;
        this.duration = duration;
    }

    @Override
    public String eventName() {
        return "course.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("name", name);
            put("duration", duration);
        }};
    }

    @Override
    public CourseCreatedDomainEvent fromPrimitives(
        String aggregateId,
        HashMap<String, Serializable> body,
        String eventId,
        String occurredOn
    ) {
        return new CourseCreatedDomainEvent(
            aggregateId,
            eventId,
            occurredOn,
            (String) body.get("name"),
            (String) body.get("duration")
        );
    }

    public String name() {
        return name;
    }

    public String duration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseCreatedDomainEvent that = (CourseCreatedDomainEvent) o;
        return name.equals(that.name) &&
               duration.equals(that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, duration);
    }
}
