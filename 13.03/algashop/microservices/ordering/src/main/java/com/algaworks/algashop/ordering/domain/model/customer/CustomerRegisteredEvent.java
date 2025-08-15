package com.algaworks.algashop.ordering.domain.model.customer;

import java.time.OffsetDateTime;
import java.time.OffsetTime;

public record CustomerRegisteredEvent(CustomerId customerId, OffsetDateTime registeredAt) {
}
