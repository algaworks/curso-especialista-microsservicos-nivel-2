package com.algaworks.algashop.billing.domain.model.invoice;

import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Payer {
    private String fullName;
    private String document;
    private String phone;
    private String email;
    private Address address;
}
