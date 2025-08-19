package com.algaworks.algashop.billing.domain.model.invoice;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Address {
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;
}
