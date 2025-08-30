package com.backendguru.mvc_demo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewCustomerRequest(
        @NotNull
        @Size(min = 3, max = 10, message = "{customer.insert.name.size}")
        String name,
        @Email (message = "{customer.insert.email.valid}")
        String email
) {
}
