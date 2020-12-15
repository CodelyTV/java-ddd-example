package tv.codely.backoffice.notifications.infrastructure;

import tv.codely.backoffice.notifications.domain.BackofficeNotification;
import tv.codely.backoffice.notifications.domain.BackofficeNotificationSender;
import tv.codely.shared.domain.Service;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Service
public final class TwitterBackofficeNotificationSender implements BackofficeNotificationSender {
    @Override
    public void send(BackofficeNotification notification) {
        try {
            final String tweet = String.format("%s \n %s", notification.title(), notification.body());
            this.createTweet(tweet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createTweet(String tweet) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        twitter.updateStatus(tweet);
    }
}
