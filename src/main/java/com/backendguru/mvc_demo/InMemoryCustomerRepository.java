package com.backendguru.mvc_demo;

import com.backendguru.mvc_demo.model.domain.Customer;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryCustomerRepository {

    private final Map<Long, Customer> db = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    Customer insert(String name, String email){
        Customer customer = new Customer(seq.getAndIncrement(), name, email, LocalDateTime.now(), ZonedDateTime.now());
        db.put(customer.getId(), customer);
        return customer;
    }

    Customer get(long id) {
        return db.get(id);
    }

    Customer update(long id, String email) {
        Customer customer = db.get(id);
        if(customer == null) {
            throw new IllegalArgumentException("Customer with id %d not found".formatted(id));
        }
        customer = new Customer(customer.getId(), customer.getName(), email, customer.getCreatedAt(), ZonedDateTime.now());
        db.put(customer.getId(), customer);
        return customer;
    }

    void delete(long id) {
        db.remove(id);
    }

    List<Customer> list(int page, int size) {
        return db.values().stream().skip(page * size).limit(size).toList();
    }

}
