package tv.codely.mooc.notifications.domain;

import tv.codely.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public final class NewCoursesNewsletterEmailSent extends DomainEvent {
    private final String studentId;

    public NewCoursesNewsletterEmailSent() {
        super(null);

        this.studentId = null;
    }

    public NewCoursesNewsletterEmailSent(String aggregateId, String studentId) {
        super(aggregateId);

        this.studentId = studentId;
    }

    public NewCoursesNewsletterEmailSent(
        String aggregateId,
        String eventId,
        String occurredOn,
        String studentId
    ) {
        super(aggregateId, eventId, occurredOn);

        this.studentId = studentId;
    }

    @Override
    public String eventName() {
        return "new_courses_newsletter_email.sent";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("student_id", studentId);
        }};
    }

    @Override
    public NewCoursesNewsletterEmailSent fromPrimitives(
        String aggregateId,
        HashMap<String, Serializable> body,
        String eventId,
        String occurredOn
    ) {
        return new NewCoursesNewsletterEmailSent(
            aggregateId,
            eventId,
            occurredOn,
            (String) body.get("student_id")
        );
    }
}
