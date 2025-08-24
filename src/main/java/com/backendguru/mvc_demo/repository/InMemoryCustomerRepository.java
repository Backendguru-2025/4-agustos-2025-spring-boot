package com.backendguru.mvc_demo.repository;

import com.backendguru.mvc_demo.model.domain.Customer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

//@Repository
public class InMemoryCustomerRepository {

    private final Map<Long, Customer> db = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public Customer insert(String name, String email){
        Customer customer = new Customer(seq.getAndIncrement(), name, email, LocalDateTime.now(), LocalDateTime.now(), new ArrayList<>());
        db.put(customer.getId(), customer);
        return customer;
    }

    public Customer get(long id) {
        return db.get(id);
    }

    public Customer update(long id, String email) {
        Customer customer = db.get(id);
        if(customer == null) {
            throw new IllegalArgumentException("Customer with id %d not found".formatted(id));
        }
        customer = new Customer(customer.getId(), customer.getName(), email, customer.getCreatedAt(), LocalDateTime.now(), new ArrayList<>());
        db.put(customer.getId(), customer);
        return customer;
    }

    public void delete(long id) {
        db.remove(id);
    }

    public List<Customer> list(int page, int size) {
        return db.values().stream().skip(page * size).limit(size).toList();
    }

}
