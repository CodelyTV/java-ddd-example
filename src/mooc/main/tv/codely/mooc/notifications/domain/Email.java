package tv.codely.mooc.notifications.domain;

import tv.codely.shared.domain.AggregateRoot;

import java.util.Objects;

public abstract class Email extends AggregateRoot {
    private final EmailId id;
    private final String  from;
    private final String  to;
    private final String  subject;
    private final String  body;

    public Email(EmailId id, String from, String to, String subject, String body) {
        this.id      = id;
        this.from    = from;
        this.to      = to;
        this.subject = subject;
        this.body    = body;
    }

    public EmailId id() {
        return id;
    }

    public String from() {
        return from;
    }

    public String to() {
        return to;
    }

    public String subject() {
        return subject;
    }

    public String body() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Email email = (Email) o;
        return id.equals(email.id) &&
               from.equals(email.from) &&
               to.equals(email.to) &&
               subject.equals(email.subject) &&
               body.equals(email.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, subject, body);
    }
}
