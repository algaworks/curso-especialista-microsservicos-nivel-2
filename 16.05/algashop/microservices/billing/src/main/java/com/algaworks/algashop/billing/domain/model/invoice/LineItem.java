package com.algaworks.algashop.billing.domain.model.invoice;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LineItem {
    private Integer number;
    private String name;
    private BigDecimal amount;
}
