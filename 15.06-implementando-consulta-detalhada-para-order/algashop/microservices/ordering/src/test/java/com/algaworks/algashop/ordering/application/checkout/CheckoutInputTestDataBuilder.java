package com.algaworks.algashop.ordering.application.checkout;

import com.algaworks.algashop.ordering.application.commons.AddressData;
import com.algaworks.algashop.ordering.domain.model.customer.CustomerTestDataBuilder; // Usado para referência de IDs padrão, se necessário
import java.util.UUID;

import static com.algaworks.algashop.ordering.domain.model.shoppingcart.ShoppingCartTestDataBuilder.DEFAULT_SHOPPING_CART_ID;

public class CheckoutInputTestDataBuilder {

    public static CheckoutInput.CheckoutInputBuilder aCheckoutInput() {
        return CheckoutInput.builder()
                .shoppingCartId(UUID.randomUUID())
                .paymentMethod("CREDIT_CARD")
                .shipping(ShippingInput.builder()
                        .recipient(RecipientData.builder()
                                .firstName("John")
                                .lastName("Doe")
                                .document("255-08-0578")
                                .phone("478-256-2604")
                                .build())
                        .address(AddressData.builder()
                                .street("Elm Street")
                                .number("456")
                                .complement("House A")
                                .neighborhood("Central Park")
                                .city("Springfield")
                                .state("Illinois")
                                .zipCode("62704")
                                .build())
                        .build())
                .billing(BillingData.builder()
                        .firstName("Matt")
                        .lastName("Damon")
                        .phone("123-321-1112")
                        .document("123-45-6789")
                        .email("matt.damon@email.com")
                        .address(AddressData.builder()
                                .street("Amphitheatre Parkway")
                                .number("1600")
                                .complement("")
                                .neighborhood("Mountain View")
                                .city("Mountain View")
                                .state("California")
                                .zipCode("94043")
                                .build())
                        .build());
    }
}
