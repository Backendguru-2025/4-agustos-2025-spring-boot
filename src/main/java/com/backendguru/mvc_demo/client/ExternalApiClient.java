package com.backendguru.mvc_demo.client;

import com.backendguru.mvc_demo.model.dto.CustomerDto;
import com.backendguru.mvc_demo.model.dto.UpdateCustomerRequest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Component
public class ExternalApiClient {

    private final RestTemplate restTemplate;
    private final String baseUrl = "https://api.example.com";

    public ExternalApiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .connectTimeout(Duration.ofSeconds(5))
                .readTimeout(Duration.ofSeconds(5))
                .build();
    }

    public CustomerDto getCustomerFromExternalApi(Long customerId) {
        String url = baseUrl + "/customers/" + customerId;
        ResponseEntity<CustomerDto> response = restTemplate.getForEntity(url, CustomerDto.class);
        return response.getBody();
    }

    public void updateCustomerInExternalApi(Long customerId, String email) {
        String url = baseUrl + "/customers/" + customerId;
        restTemplate.put(url, new UpdateCustomerRequest(email));
    }
}

