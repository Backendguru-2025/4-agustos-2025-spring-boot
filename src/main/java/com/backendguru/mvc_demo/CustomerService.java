package com.backendguru.mvc_demo;

import com.backendguru.mvc_demo.model.domain.Customer;
import com.backendguru.mvc_demo.model.dto.CustomerDto;
import com.backendguru.mvc_demo.model.dto.NewCustomerRequest;
import com.backendguru.mvc_demo.model.dto.UpdateCustomerRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final InMemoryCustomerRepository inMemoryCustomerRepository;
    public CustomerService(InMemoryCustomerRepository inMemoryCustomerRepository) {
        this.inMemoryCustomerRepository = inMemoryCustomerRepository;
    }

    public Customer create(NewCustomerRequest request){
        return inMemoryCustomerRepository.insert(request.name(), request.email());
    }

    public Customer read(long id){
        return inMemoryCustomerRepository.get(id);
    }

    public Customer update(long id, UpdateCustomerRequest request) {
        return inMemoryCustomerRepository.update(id, request.email());
    }

    public void delete(long id){
        Customer customer = inMemoryCustomerRepository.get(id);
        if(customer == null) {
            throw new IllegalArgumentException("Customer with id %d not found".formatted(id));
        }
        inMemoryCustomerRepository.delete(id);
    }

    public List<Customer> list(int page, int size) {
        return inMemoryCustomerRepository.list(page, size);
    }
}
