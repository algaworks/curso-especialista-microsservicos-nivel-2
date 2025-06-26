package com.algaworks.algashop.ordering.domain.exception;

import com.algaworks.algashop.ordering.domain.entity.Order;
import com.algaworks.algashop.ordering.domain.valueobject.id.OrderId;

import static com.algaworks.algashop.ordering.domain.exception.ErrorMessages.*;

public class OrderCannotBePlacedException extends DomainException {

    private OrderCannotBePlacedException(String message) {
        super(message);
    }

    public static OrderCannotBePlacedException noItems(OrderId id) {
        return new OrderCannotBePlacedException(
                String.format(ERROR_ORDER_CANNOT_BE_PLACED_HAS_NO_ITEMS, id)
        );
    }

    public static OrderCannotBePlacedException noShippingInfo(OrderId id) {
        return new OrderCannotBePlacedException(String.format(ERROR_ORDER_CANNOT_BE_PLACED_HAS_NO_SHIPPING_INFO, id));
    }

    public static OrderCannotBePlacedException noBillingInfo(OrderId id) {
        return new OrderCannotBePlacedException(String.format(ERROR_ORDER_CANNOT_BE_PLACED_HAS_NO_BILLING_INFO, id));
    }

    public static OrderCannotBePlacedException invalidShippingCost(OrderId id) {
        return new OrderCannotBePlacedException(String.format(ERROR_ORDER_CANNOT_BE_PLACED_INVALID_SHIPPING_COST, id));
    }

    public static OrderCannotBePlacedException invalidExpectedDeliveryDate(OrderId id) {
        return new OrderCannotBePlacedException(String.format(ERROR_ORDER_CANNOT_BE_PLACED_INVALID_DELIVERY_DATE, id));
    }

    public static OrderCannotBePlacedException noPaymentMethod(OrderId id) {
        return new OrderCannotBePlacedException(String.format(ERROR_ORDER_CANNOT_BE_PLACED_HAS_NO_PAYMENT_METHOD, id));
    }
}
