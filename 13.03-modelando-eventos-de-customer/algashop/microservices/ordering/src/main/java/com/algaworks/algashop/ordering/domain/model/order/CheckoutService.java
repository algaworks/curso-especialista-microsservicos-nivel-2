package com.algaworks.algashop.ordering.domain.model.order;

import com.algaworks.algashop.ordering.domain.model.shoppingcart.ShoppingCart;
import com.algaworks.algashop.ordering.domain.model.shoppingcart.ShoppingCartItem;
import com.algaworks.algashop.ordering.domain.model.shoppingcart.ShoppingCartCantProceedToCheckoutException;
import com.algaworks.algashop.ordering.domain.model.DomainService;
import com.algaworks.algashop.ordering.domain.model.product.Product;

import java.util.Set;

@DomainService
public class CheckoutService {

	public Order checkout(ShoppingCart shoppingCart,
						  Billing billing,
						  Shipping shipping,
						  PaymentMethod paymentMethod) {
		if (shoppingCart.isEmpty()) {
			throw new ShoppingCartCantProceedToCheckoutException();
		}

		if (shoppingCart.containsUnavailableItems()) {
			throw new ShoppingCartCantProceedToCheckoutException();
		}

		Set<ShoppingCartItem> items = shoppingCart.items();
		
		Order order = Order.draft(shoppingCart.customerId());
		order.changeBilling(billing);
		order.changeShipping(shipping);
		order.changePaymentMethod(paymentMethod);

		for (ShoppingCartItem item : items) {
			order.addItem(new Product(item.productId(), item.name(),
				item.price(), item.isAvailable()), item.quantity());
		}

		order.place();
		shoppingCart.empty();

		return order;
	}

}
