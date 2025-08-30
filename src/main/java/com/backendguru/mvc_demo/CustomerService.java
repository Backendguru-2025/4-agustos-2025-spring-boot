package com.backendguru.mvc_demo;

import com.backendguru.mvc_demo.model.domain.Customer;
import com.backendguru.mvc_demo.model.dto.NewCustomerRequest;
import com.backendguru.mvc_demo.model.dto.UpdateCustomerRequest;
import com.backendguru.mvc_demo.model.entity.CustomerEntity;
import com.backendguru.mvc_demo.model.entity.Profile;
import com.backendguru.mvc_demo.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {

    private static final CustomerMapper CUSTOMER_MAPPER = CustomerMapper.INSTANCE;
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(NewCustomerRequest request){
        CustomerEntity customerEntity = CUSTOMER_MAPPER.toEntity(
                new Customer(
                        null,
                        request.name(),
                        request.email(),
                        null,
                        null,
                        new ArrayList<>()));

        customerEntity = customerRepository.save(customerEntity);
        return  CUSTOMER_MAPPER.toDomain(customerEntity);
    }

    @PreAuthorize("hasRole('DEVELOPER')")
    public Customer read(long id){
        CustomerEntity customerEntity = getCustomerEntity(id);
        return CUSTOMER_MAPPER.toDomain(customerEntity);
    }

    private CustomerEntity getCustomerEntity(long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Customer with id %d not found".formatted(id))
        );
    }

    public Customer update(long id, UpdateCustomerRequest request) {
        CustomerEntity customerEntity = getCustomerEntity(id);
        if (Objects.isNull(customerEntity.getProfile())) {
            Profile profile = new Profile();
            customerEntity.setProfile(profile);
        }
        String newEmail = request.email();
        if (newEmail.endsWith("@gmail.com")
                && !newEmail.equals(customerEntity.getProfile().getEmail())) {
            newEmail = newEmail.replace("@gmail.com", "@yahoo.com");
        }
        customerEntity.getProfile().setEmail(newEmail);
        CustomerEntity updatedEntity = customerRepository.save(customerEntity);
        return CUSTOMER_MAPPER.toDomain(updatedEntity);
    }

    public void delete(long id){
        CustomerEntity customerEntity = getCustomerEntity(id);
        customerRepository.delete(customerEntity);
    }

    public List<Customer> list(int page, int size) {
        Page<CustomerEntity> pageResult = customerRepository.findAll(Pageable.ofSize(size).withPage(page));
        return pageResult.stream().map(CUSTOMER_MAPPER::toDomain).toList();
    }
}
