package tv.codely.backoffice.notifications.infrastructure;

import tv.codely.backoffice.notifications.domain.BackofficeNotification;
import tv.codely.backoffice.notifications.domain.BackofficeNotificationSender;
import tv.codely.shared.domain.Service;
import tv.codely.shared.infrastructure.config.Parameter;
import tv.codely.shared.infrastructure.config.ParameterNotExist;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Service
public final class TwitterBackofficeNotificationSender implements BackofficeNotificationSender {
    private final Parameter config;

    public TwitterBackofficeNotificationSender(Parameter config) {
        this.config = config;
    }

    @Override
    public void send(BackofficeNotification notification) {
        try {
            final String tweet = String.format("%s \n %s", notification.title(), notification.body());
            this.createTweet(tweet);
        } catch (Exception | ParameterNotExist e) {
            e.printStackTrace();
        }
    }


    private void createTweet(String tweet) throws TwitterException, ParameterNotExist {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder
            .setOAuthConsumerKey(config.get("BACKOFFICE_OAUTH_CONSUMER_KEY"))
            .setOAuthConsumerSecret(config.get("BACKOFFICE_OAUTH_CONSUMER_SECRET"))
            .setOAuthAccessToken(config.get("BACKOFFICE_OAUTH_ACCESS_TOKEN"))
            .setOAuthAccessTokenSecret(config.get("BACKOFFICE_OAUTH_ACCESS_TOKEN_SECRET"));
        TwitterFactory twitterFactory = new TwitterFactory(configurationBuilder.build());
        Twitter        twitter        = twitterFactory.getInstance();
        twitter.updateStatus(tweet);
    }
}
