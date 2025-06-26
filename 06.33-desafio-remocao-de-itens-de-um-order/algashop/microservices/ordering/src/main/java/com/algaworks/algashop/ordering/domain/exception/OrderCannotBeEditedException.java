package com.algaworks.algashop.ordering.domain.exception;

import com.algaworks.algashop.ordering.domain.entity.OrderStatus;
import com.algaworks.algashop.ordering.domain.valueobject.id.OrderId;

public class OrderCannotBeEditedException extends DomainException {

	public OrderCannotBeEditedException(OrderId id, OrderStatus status) {
		super(String.format(ErrorMessages.ERROR_ORDER_CANNOT_BE_EDITED, id, status));
	}
}