package com.algaworks.algashop.billing.domain.model.invoice;

import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PaymentSettings {

    @EqualsAndHashCode.Include
    private UUID id;
    private UUID creditCardId;
    private String gatewayCode;
    private PaymentMethod method;
}
