package tv.codely.mooc.notifications.domain;

import tv.codely.shared.domain.UuidMother;

public final class EmailIdMother {
    public static EmailId create(String value) {
        return new EmailId(value);
    }

    public static EmailId random() {
        return create(UuidMother.random());
    }
}
