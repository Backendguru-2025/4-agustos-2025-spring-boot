package com.backendguru.mvc_demo.model.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@JsonTest
class NewCustomerRequestJsonTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void serializeShouldMatch() throws JsonProcessingException {
        NewCustomerRequest newCustomerRequest = new NewCustomerRequest("deneme-name", "deneme-email");
        String result = objectMapper.writeValueAsString(newCustomerRequest);
        assertThat(result).contains("\"name\":\"deneme-name\"");
        assertThat(result).contains("\"email\":\"deneme-email\"");

    }

}