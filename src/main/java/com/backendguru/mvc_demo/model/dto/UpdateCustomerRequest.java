package com.backendguru.mvc_demo.model.dto;

import jakarta.validation.constraints.Email;

public record UpdateCustomerRequest(
        @Email (message = "{customer.update.email.valid}")
        String email) {
}
