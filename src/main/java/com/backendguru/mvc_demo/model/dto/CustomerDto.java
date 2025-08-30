package com.backendguru.mvc_demo.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.List;

public record CustomerDto(
        @JsonProperty("Name")
        String name,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<ReviewDto> reviews) {
}
