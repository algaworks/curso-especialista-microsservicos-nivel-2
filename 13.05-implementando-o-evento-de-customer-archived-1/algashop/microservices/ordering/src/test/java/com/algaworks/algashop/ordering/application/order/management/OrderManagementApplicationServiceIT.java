package com.algaworks.algashop.ordering.application.order.management;

import com.algaworks.algashop.ordering.domain.model.customer.CustomerTestDataBuilder;
import com.algaworks.algashop.ordering.domain.model.customer.Customers;
import com.algaworks.algashop.ordering.domain.model.order.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
class OrderManagementApplicationServiceIT {

    @Autowired
    private OrderManagementApplicationService service;

    @Autowired
    private Orders orders;

    @Autowired
    private Customers customers;

    @BeforeEach
    public void setup() {
        if (!customers.exists(CustomerTestDataBuilder.DEFAULT_CUSTOMER_ID)) {
            customers.add(CustomerTestDataBuilder.existingCustomer().build());
        }
    }

    @Test
    void shouldCancelOrderSuccessfully() {
        Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.PLACED).build();
        orders.add(order);

        service.cancel(order.id().toString());

        Optional<Order> updatedOrder = orders.ofId(order.id());
        Assertions.assertThat(updatedOrder).isPresent();
        Assertions.assertThat(updatedOrder.get().status()).isEqualTo(OrderStatus.CANCELED);
        Assertions.assertThat(updatedOrder.get().canceledAt()).isNotNull();
    }

    @Test
    void shouldThrowOrderNotFoundExceptionWhenCancellingNonExistingOrder() {
        String nonExistingOrderId = new OrderId().toString();

        Assertions.assertThatExceptionOfType(OrderNotFoundException.class)
                .isThrownBy(() -> service.cancel(nonExistingOrderId));
    }

    @Test
    void shouldThrowOrderStatusCannotBeChangedExceptionWhenCancellingAlreadyCanceledOrder() {
        Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.CANCELED).build();
        orders.add(order);

        Assertions.assertThatExceptionOfType(OrderStatusCannotBeChangedException.class)
                .isThrownBy(() -> service.cancel(order.id().toString()));
    }

    @Test
    void shouldMarkOrderAsPaidSuccessfully() {
        Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.PLACED).build();
        orders.add(order);

        service.markAsPaid(order.id().toString());

        Optional<Order> updatedOrder = orders.ofId(order.id());
        Assertions.assertThat(updatedOrder).isPresent();
        Assertions.assertThat(updatedOrder.get().status()).isEqualTo(OrderStatus.PAID);
        Assertions.assertThat(updatedOrder.get().paidAt()).isNotNull();
    }

    @Test
    void shouldThrowOrderNotFoundExceptionWhenMarkingNonExistingOrderAsPaid() {
        String nonExistingOrderId = new OrderId().toString();

        Assertions.assertThatExceptionOfType(OrderNotFoundException.class)
                .isThrownBy(() -> service.markAsPaid(nonExistingOrderId));
    }

    @Test
    void shouldThrowOrderStatusCannotBeChangedExceptionWhenMarkingAlreadyPaidOrderAsPaid() {
        Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.PAID).build();
        orders.add(order);

        Assertions.assertThatExceptionOfType(OrderStatusCannotBeChangedException.class)
                .isThrownBy(() -> service.markAsPaid(order.id().toString()));
    }

    @Test
    void shouldThrowOrderStatusCannotBeChangedExceptionWhenMarkingCanceledOrderAsPaid() {
        Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.CANCELED).build();
        orders.add(order);

        Assertions.assertThatExceptionOfType(OrderStatusCannotBeChangedException.class)
                .isThrownBy(() -> service.markAsPaid(order.id().toString()));
    }

    @Test
    void shouldMarkOrderAsReadySuccessfully() {
        Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.PAID).build();
        orders.add(order);

        service.markAsReady(order.id().toString());

        Optional<Order> updatedOrder = orders.ofId(order.id());
        Assertions.assertThat(updatedOrder).isPresent();
        Assertions.assertThat(updatedOrder.get().status()).isEqualTo(OrderStatus.READY);
        Assertions.assertThat(updatedOrder.get().readyAt()).isNotNull();
    }

    @Test
    void shouldThrowOrderNotFoundExceptionWhenMarkingNonExistingOrderAsReady() {
        String nonExistingOrderId = new OrderId().toString();

        Assertions.assertThatExceptionOfType(OrderNotFoundException.class)
                .isThrownBy(() -> service.markAsReady(nonExistingOrderId));
    }

    @Test
    void shouldThrowOrderStatusCannotBeChangedExceptionWhenMarkingAlreadyReadyOrderAsReady() {
        Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.READY).build();
        orders.add(order);

        Assertions.assertThatExceptionOfType(OrderStatusCannotBeChangedException.class)
                .isThrownBy(() -> service.markAsReady(order.id().toString()));
    }

    @Test
    void shouldThrowOrderStatusCannotBeChangedExceptionWhenMarkingPlacedOrderAsReady() {
        Order order = OrderTestDataBuilder.anOrder().status(OrderStatus.PLACED).build();
        orders.add(order);

        Assertions.assertThatExceptionOfType(OrderStatusCannotBeChangedException.class)
                .isThrownBy(() -> service.markAsReady(order.id().toString()));
    }
}
