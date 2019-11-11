package tv.codely.shared.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import tv.codely.shared.domain.UuidGenerator;
import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.domain.bus.event.EventBus;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public abstract class UnitTestCase {
    protected EventBus      eventBus;
    protected UuidGenerator uuidGenerator;

    @BeforeEach
    protected void setUp() {
        eventBus = mock(EventBus.class);
        uuidGenerator = mock(UuidGenerator.class);
    }

    public void shouldHavePublished(List<DomainEvent> domainEvents) {
        verify(eventBus, atLeastOnce()).publish(domainEvents);
    }

    public void shouldHavePublished(DomainEvent domainEvent) {
        shouldHavePublished(Collections.singletonList(domainEvent));
    }

    public void shouldGenerateUuid(String uuid) {
        when(uuidGenerator.generate()).thenReturn(uuid);
    }
}
