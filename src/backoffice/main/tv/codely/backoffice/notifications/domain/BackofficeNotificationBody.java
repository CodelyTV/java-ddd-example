package tv.codely.backoffice.notifications.domain;

import tv.codely.shared.domain.StringValueObject;

public final class BackofficeNotificationBody extends StringValueObject {
    public BackofficeNotificationBody(String value) {
        super(value);
    }

    public BackofficeNotificationBody() {
        super("");
    }
}
