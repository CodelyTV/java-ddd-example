package tv.codely.shared.domain.criteria;

import java.util.Optional;

public final class Order {
    private final OrderBy   orderBy;
    private final OrderType orderType;

    public Order(OrderBy orderBy, OrderType orderType) {
        this.orderBy   = orderBy;
        this.orderType = orderType;
    }

    public static Order fromValues(Optional<String> orderBy, Optional<String> orderType) {
        return orderBy.map(order -> new Order(new OrderBy(order), OrderType.valueOf(orderType.orElse("ASC"))))
                      .orElseGet(Order::none);
    }

    private static Order none() {
        return new Order(new OrderBy(""), OrderType.NONE);
    }

    public OrderBy orderBy() {
        return orderBy;
    }

    public OrderType orderType() {
        return orderType;
    }

    public boolean hasOrder() {
        return !orderType.isNone();
    }
}
