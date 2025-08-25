package com.backendguru.mvc_demo;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {

        return Health.down().withDetail("custom-health-implementation", LocalDateTime.now()).build();
    }
}
