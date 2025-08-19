package com.algaworks.algashop.ordering.infrastructure.persistence.customer;

import com.algaworks.algashop.ordering.application.customer.query.CustomerOutput;
import com.algaworks.algashop.ordering.application.customer.query.CustomerQueryService;
import com.algaworks.algashop.ordering.application.utility.Mapper;
import com.algaworks.algashop.ordering.domain.model.customer.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomerQueryServiceImpl implements CustomerQueryService {

    private final CustomerPersistenceEntityRepository repository;
    private final Mapper mapper;

    @Override
    public CustomerOutput findById(UUID customerId) {
        CustomerPersistenceEntity customer = repository.findById(customerId).orElseThrow(()-> new CustomerNotFoundException());
        return mapper.convert(customer, CustomerOutput.class);
    }
}
