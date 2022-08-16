package tv.codely.shared.infrastructure.bus.event.rabbitmq;

public final class RabbitMqExchangeNameFormatter {
    public static String retry(String exchangeName) {
        return String.format("retry-%s", exchangeName);
    }

    public static String deadLetter(String exchangeName) {
        return String.format("dead_letter-%s", exchangeName);
    }
}
