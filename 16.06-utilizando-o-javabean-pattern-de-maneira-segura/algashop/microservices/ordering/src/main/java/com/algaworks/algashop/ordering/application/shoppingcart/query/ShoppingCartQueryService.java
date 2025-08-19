package com.algaworks.algashop.ordering.application.shoppingcart.query;

import com.algaworks.algashop.ordering.domain.model.shoppingcart.ShoppingCart;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ShoppingCartQueryService {
    ShoppingCartOutput findById(UUID shoppingCartId);
    Page<ShoppingCartSummaryOutput> filter(ShoppingCart filter);
}
