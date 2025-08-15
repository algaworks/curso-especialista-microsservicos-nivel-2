package com.algaworks.algashop.ordering.application.order.query;

public interface OrderQueryService {
    OrderDetailOutput findById(String id);
}
