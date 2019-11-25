package tv.codely.mooc.notifications.application;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;
import tv.codely.mooc.notifications.domain.Email;
import tv.codely.mooc.notifications.domain.EmailSender;
import tv.codely.shared.infrastructure.UnitTestCase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public abstract class NotificationsModuleUnitTestCase extends UnitTestCase {
    protected EmailSender sender;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        sender = mock(EmailSender.class);
    }

    public void shouldHaveSentEmail(Email email) {
        ArgumentCaptor<Email> argument = ArgumentCaptor.forClass(Email.class);

        verify(sender, atLeastOnce()).send(argument.capture());

        List<Email> emails = argument.getAllValues();

        assertTrue(emails.contains(email));
    }
}
