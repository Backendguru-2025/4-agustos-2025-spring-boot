package com.backendguru.mvc_demo.web;

import com.backendguru.mvc_demo.CustomerService;
import com.backendguru.mvc_demo.model.domain.Customer;
import com.backendguru.mvc_demo.model.dto.CustomerDto;
import com.backendguru.mvc_demo.model.dto.NewCustomerRequest;
import com.backendguru.mvc_demo.model.dto.ReviewDto;
import com.backendguru.mvc_demo.model.dto.UpdateCustomerRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/customers", headers = "Accept=application/vnd.backendguru.v1+json")
public class CustomerController {

    private final CustomerService customerService;
    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    CustomerDto getCustomer(@PathVariable long id, @RequestHeader(required = false) Integer version){
        Customer customer = customerService.read(id);
        return new CustomerDto(
                customer.getName(),
                customer.getEmail(),
                customer.getCreatedAt(),
                customer.getUpdatedAt(),
                Collections.emptyList());
    }

    @PostMapping
    @ResponseStatus(code = org.springframework.http.HttpStatus.CREATED)
    CustomerDto newCustomer(@Valid @RequestBody NewCustomerRequest request){
        Customer customer = customerService.create(request);
        return new CustomerDto(
                customer.getName(),
                customer.getEmail(),
                customer.getCreatedAt(),
                customer.getUpdatedAt(),
                Collections.emptyList());

    }

    @PutMapping("/{id}")
    CustomerDto updateCustomer(@Validated @RequestBody UpdateCustomerRequest request, @PathVariable long id){
        Customer updated = customerService.update(id, request);
        return new CustomerDto(
                updated.getName(),
                updated.getEmail(),
                updated.getCreatedAt(),
                updated.getUpdatedAt(),
                Collections.emptyList());

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCustomer(@PathVariable long id){
        customerService.delete(id);
    }

    @GetMapping
    List<CustomerDto> listCustomers(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size
    ){
        return customerService.list(page, size).stream()
                .map( c -> new CustomerDto(
                        c.getName(),
                        c.getEmail(),
                        c.getCreatedAt(),
                        c.getUpdatedAt(),
                        c.getReviews().stream()
                                .map(
                                        review -> new ReviewDto(
                                                review.getId(),
                                                review.getComment())).toList()
                        )
                        ).toList();
    }

}
