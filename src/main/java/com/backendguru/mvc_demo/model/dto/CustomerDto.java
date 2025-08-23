package com.backendguru.mvc_demo.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

public record CustomerDto(
        String name,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String email,
        LocalDateTime createdAt,
        ZonedDateTime updatedAt,
        List<ReviewDto> reviews) {
}
