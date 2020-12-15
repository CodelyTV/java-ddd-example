package tv.codely.backoffice.notifications.application.send_new_course_newsletter;

import tv.codely.backoffice.notifications.domain.*;

public final class NewBackofficeCourseNewsletterSender {
    private final BackofficeNotificationSender sender;
    private static final String TITLE = "We launches %s!";
    private static final String BODY = "Access pro.codely.tv with your subscription and enjoy it!";


    public NewBackofficeCourseNewsletterSender(BackofficeNotificationSender sender) {
        this.sender = sender;
    }

    public void send(String name){

        BackofficeNotificationTitle title = new BackofficeNotificationTitle(String.format(TITLE, name));
        BackofficeNotificationBody body = new BackofficeNotificationBody(BODY);
        BackofficeNotification notification = new BackofficeNotification(title, body);

        this.sender.send(notification);
    }
}
