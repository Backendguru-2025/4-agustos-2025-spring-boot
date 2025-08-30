package com.backendguru.mvc_demo.web;

import com.backendguru.mvc_demo.model.dto.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
properties = "spring.security.user.password=password")
class CustomerControllerTestRestTemplateIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getCustomer() {
        String url = "http://localhost:" + port + "/customers/1";
        ResponseEntity<CustomerDto> responseEntity = testRestTemplate
                .withBasicAuth("user", "password")
                .getForEntity(url, CustomerDto.class);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(responseEntity.getBody().name()).isEqualTo("John");
    }

    @Test
    void shouldReturnProblemDetailForEntityNotFound() {
        String url = "http://localhost:" + port + "/customers/1000";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.backendguru.v1+json");

        ResponseEntity<ProblemDetail> responseEntity = testRestTemplate
                .withBasicAuth("user", "password")
                .exchange(
                        url,
                        HttpMethod.GET,
                        new HttpEntity<>(headers),
                        ProblemDetail.class
                );

        assertThat(responseEntity.getStatusCode().isSameCodeAs(HttpStatusCode.valueOf(404))).isTrue();
        assertThat(responseEntity.getBody().getTitle()).isEqualTo("Resource not found");
        assertThat(responseEntity.getBody().getDetail()).isEqualTo("Customer with id 1000 not found");
        assertThat(responseEntity.getBody().getStatus()).isEqualTo(404);
    }

    @Test
    void actuatorHealth() {
        String url = "http://localhost:" + port + "/actuator/health";
        ResponseEntity<String> responseEntity = testRestTemplate.getForEntity(url, String.class);
        assertThat(responseEntity.getStatusCode().is5xxServerError()).isTrue();
        assertThat(responseEntity.getBody()).contains("DOWN");
    }

}
