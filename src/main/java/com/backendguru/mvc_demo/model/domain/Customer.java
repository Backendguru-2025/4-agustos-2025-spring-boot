package com.backendguru.mvc_demo.model.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

public class Customer {

    private final long id;
    private final String name;
    private final String email;
    private final LocalDateTime createdAt;
    private final ZonedDateTime updatedAt;

    public Customer(long id, String name, String email, LocalDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }
}
