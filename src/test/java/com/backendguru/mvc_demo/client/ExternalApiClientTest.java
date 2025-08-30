package com.backendguru.mvc_demo.client;

import com.backendguru.mvc_demo.model.dto.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(ExternalApiClient.class)
class ExternalApiClientTest {

    @Autowired
    private ExternalApiClient client;

    @Autowired
    private MockRestServiceServer server;

    @Test
    void shouldGetCustomerFromExternalApi() {
        // Given
        String customerId = "1";
        String expectedUrl = "https://api.example.com/customers/" + customerId;
        String responseBody =
                """
                {
                    "Name": "John Doe",
                    "email": "john.doe@example.com",
                    "createdAt": "2025-01-01T12:00:00",
                    "updatedAt": "2025-01-01T12:00:00",
                    "reviews": []
                }
                """;

        // Setup mock server
        server.expect(requestTo(expectedUrl))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

        // When
        CustomerDto customerDto = client.getCustomerFromExternalApi(1L);

        // Then
        assertThat(customerDto).isNotNull();
        assertThat(customerDto.name()).isEqualTo("John Doe");
        assertThat(customerDto.email()).isEqualTo("john.doe@example.com");

        // Verify all expectations met
        server.verify();
    }

    @Test
    void shouldUpdateCustomerInExternalApi() {
        // Given
        String customerId = "1";
        String email = "updated@example.com";
        String expectedUrl = "https://api.example.com/customers/" + customerId;
        String expectedRequestBody = "{\"email\":\"updated@example.com\"}";

        // Setup mock server
        server.expect(requestTo(expectedUrl))
                .andExpect(method(HttpMethod.PUT))
                .andExpect(content().json(expectedRequestBody))
                .andRespond(MockRestResponseCreators.withNoContent());

        // When
        client.updateCustomerInExternalApi(1L, email);

        // Then
        server.verify();
    }
}

