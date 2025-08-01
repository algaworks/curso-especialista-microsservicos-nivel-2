package com.algaworks.algashop.ordering.domain.model.service;

import com.algaworks.algashop.ordering.domain.model.entity.ShoppingCart;
import com.algaworks.algashop.ordering.domain.model.exception.CustomerAlreadyHaveShoppingCartException;
import com.algaworks.algashop.ordering.domain.model.exception.CustomerNotFoundException;
import com.algaworks.algashop.ordering.domain.model.repository.Customers;
import com.algaworks.algashop.ordering.domain.model.repository.ShoppingCarts;
import com.algaworks.algashop.ordering.domain.model.utility.DomainService;
import com.algaworks.algashop.ordering.domain.model.valueobject.id.CustomerId;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class ShoppingService {
	
	private final ShoppingCarts shoppingCarts;
	private final Customers customers;

	public ShoppingCart startShopping(CustomerId customerId) {
		if (!customers.exists(customerId)) {
			throw new CustomerNotFoundException();
		}

		if (shoppingCarts.ofCustomer(customerId).isPresent()) {
			throw new CustomerAlreadyHaveShoppingCartException();
		}

		return ShoppingCart.startShopping(customerId);
	}

}