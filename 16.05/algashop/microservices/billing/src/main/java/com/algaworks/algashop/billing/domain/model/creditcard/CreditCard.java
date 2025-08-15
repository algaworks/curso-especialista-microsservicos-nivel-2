package com.algaworks.algashop.billing.domain.model.creditcard;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Setter(AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreditCard {
    @EqualsAndHashCode.Include
    private UUID id;
    private OffsetDateTime createdAt;
    private UUID customerId;
    private String lastNumbers;
    private String brand;
    private Integer expMonth;
    private Integer expYear;

    @Setter(AccessLevel.PUBLIC)
    private String gatewayCode;
}
