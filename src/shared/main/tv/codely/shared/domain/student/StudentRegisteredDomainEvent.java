package tv.codely.shared.domain.student;

import tv.codely.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class StudentRegisteredDomainEvent extends DomainEvent {
    private final String name;
    private final String surname;
    private final String email;

    public StudentRegisteredDomainEvent() {
        super(null);

        this.name    = null;
        this.surname = null;
        this.email   = null;
    }

    public StudentRegisteredDomainEvent(String aggregateId, String name, String surname, String email) {
        super(aggregateId);

        this.name    = name;
        this.surname = surname;
        this.email   = email;
    }

    public StudentRegisteredDomainEvent(
        String aggregateId,
        String eventId,
        String occurredOn,
        String name,
        String surname,
        String email
    ) {
        super(aggregateId, eventId, occurredOn);

        this.name    = name;
        this.surname = surname;
        this.email   = email;
    }

    @Override
    public String eventName() {
        return "student.registered";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("name", name);
            put("surname", surname);
            put("email", email);
        }};
    }

    @Override
    public StudentRegisteredDomainEvent fromPrimitives(
        String aggregateId,
        HashMap<String, Serializable> body,
        String eventId,
        String occurredOn
    ) {
        return new StudentRegisteredDomainEvent(
            aggregateId,
            eventId,
            occurredOn,
            (String) body.get("name"),
            (String) body.get("surname"),
            (String) body.get("email")
        );
    }

    public String name() {
        return name;
    }

    public String surname() {
        return surname;
    }

    public String email() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StudentRegisteredDomainEvent that = (StudentRegisteredDomainEvent) o;
        return name.equals(that.name) &&
            surname.equals(that.surname) &&
            email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, email);
    }
}
