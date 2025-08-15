package com.algaworks.algashop.ordering.application.shoppingcart.query;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ShoppingCartSummaryOutput {
	private UUID id;
	private UUID customerId;
	private Integer totalItems;
	private BigDecimal totalAmount;
}
