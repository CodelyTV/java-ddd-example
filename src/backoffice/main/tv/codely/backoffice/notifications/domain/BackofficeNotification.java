package tv.codely.backoffice.notifications.domain;

public final class BackofficeNotification {
    private final BackofficeNotificationTitle title;
    private final BackofficeNotificationBody body;

    public BackofficeNotification(BackofficeNotificationTitle title, BackofficeNotificationBody body) {
        this.title = title;
        this.body  = body;
    }

    public BackofficeNotificationTitle title() {
        return this.title;
    }

    public BackofficeNotificationBody body() {
        return this.body;
    }
}
