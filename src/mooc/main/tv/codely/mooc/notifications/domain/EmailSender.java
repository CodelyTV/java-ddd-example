package tv.codely.mooc.notifications.domain;

public interface EmailSender {
    void send(Email email);
}
