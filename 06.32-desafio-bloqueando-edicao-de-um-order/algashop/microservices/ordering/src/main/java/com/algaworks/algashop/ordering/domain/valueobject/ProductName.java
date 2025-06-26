package com.algaworks.algashop.ordering.domain.valueobject;

import com.algaworks.algashop.ordering.domain.validator.FieldValidations;

public record ProductName(String value) {

	public ProductName {
		FieldValidations.requiresNonBlank(value);
	}

	@Override
	public String toString() {
		return value;
	}

}