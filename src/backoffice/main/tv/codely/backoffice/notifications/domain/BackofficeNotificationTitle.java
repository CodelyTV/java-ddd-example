package tv.codely.backoffice.notifications.domain;

import tv.codely.shared.domain.StringValueObject;

public final class BackofficeNotificationTitle extends StringValueObject {
    public BackofficeNotificationTitle(String value) {
        super(value);
    }

    public BackofficeNotificationTitle() {
        super("");
    }
}
