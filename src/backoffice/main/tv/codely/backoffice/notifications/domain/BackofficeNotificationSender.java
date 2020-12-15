package tv.codely.backoffice.notifications.domain;

public interface BackofficeNotificationSender {
    void send(BackofficeNotification notification);
}
