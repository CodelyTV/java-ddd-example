package tv.codely.shared.domain.bus.event;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface DomainEventSubscriber {
    Class<? extends DomainEvent>[] value();
}
