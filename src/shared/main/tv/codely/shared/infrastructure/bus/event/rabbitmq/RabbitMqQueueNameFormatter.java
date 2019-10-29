package tv.codely.shared.infrastructure.bus.event.rabbitmq;

import tv.codely.shared.domain.Utils;
import tv.codely.shared.infrastructure.bus.event.DomainEventSubscriberInformation;

public final class RabbitMqQueueNameFormatter {
    public static String format(DomainEventSubscriberInformation information) {
        return String.format(
            "codelytv.%s.%s.%s",
            information.contextName(),
            information.moduleName(),
            Utils.toSnake(information.className())
        );
    }

    public static String formatRetry(DomainEventSubscriberInformation information) {
        return String.format("retry.%s", format(information));
    }

    public static String formatDeadLetter(DomainEventSubscriberInformation information) {
        return String.format("dead_letter.%s", format(information));
    }
}
