package com.algaworks.algashop.ordering.application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerOutput {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String document;
    private String phone;
    private LocalDate birthDate;
    private Integer loyaltyPoints;
    private OffsetDateTime registeredAt;
    private OffsetDateTime archivedAt;
    private Boolean promotionNotificationsAllowed;
    private Boolean archived;
    private AddressData address;

}
