package com.backendguru.mvc_demo.model.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

public class Customer {

    private final Long id;
    private final String name;
    private final String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Review> reviews;

    public Customer(Long id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.reviews = reviews;
    }

    public Long getId() {
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
