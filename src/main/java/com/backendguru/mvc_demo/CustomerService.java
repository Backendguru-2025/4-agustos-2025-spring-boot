package com.backendguru.mvc_demo;

import com.backendguru.mvc_demo.model.domain.Customer;
import com.backendguru.mvc_demo.model.dto.NewCustomerRequest;
import com.backendguru.mvc_demo.model.dto.UpdateCustomerRequest;
import com.backendguru.mvc_demo.model.entity.CustomerEntity;
import com.backendguru.mvc_demo.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public abstract class CustomerService {
    public abstract Customer create(NewCustomerRequest request);

    @PreAuthorize("hasRole('DEVELOPER')")
    public abstract Customer read(long id);

    CustomerEntity getCustomerEntity(long id) {
        return getRepository().findById(id).orElseThrow(
                () -> new EntityNotFoundException("Customer with id %d not found".formatted(id))
        );
    }

    public abstract Customer update(long id, UpdateCustomerRequest request);

    public abstract void delete(long id);

    public abstract List<Customer> list(int page, int size);

    abstract CustomerRepository getRepository();
}
