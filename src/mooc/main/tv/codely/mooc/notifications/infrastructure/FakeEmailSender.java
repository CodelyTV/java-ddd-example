package tv.codely.mooc.notifications.infrastructure;

import tv.codely.mooc.notifications.domain.Email;
import tv.codely.mooc.notifications.domain.EmailSender;
import tv.codely.shared.domain.Service;

@Service
public final class FakeEmailSender implements EmailSender {
    @Override
    public void send(Email email) {
        // In the future...
    }
}
