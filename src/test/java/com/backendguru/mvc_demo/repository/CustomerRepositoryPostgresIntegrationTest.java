package com.backendguru.mvc_demo.repository;

import com.backendguru.mvc_demo.model.entity.CustomerEntity;
import com.backendguru.mvc_demo.model.entity.Profile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


@Testcontainers
@DataJpaTest
class CustomerRepositoryPostgresIntegrationTest {

    @Container
    static final PostgreSQLContainer<?> PG = new PostgreSQLContainer<>("postgres:16-alpine");

    @DynamicPropertySource
    static void registerProps(final DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", PG::getJdbcUrl);
        registry.add("spring.datasource.username", PG::getUsername);
        registry.add("spring.datasource.password", PG::getPassword);
        registry.add("spring.sql.init.platform", () -> "postgres");
        registry.add("spring.sql.init.mode", () -> "always");
        registry.add("spring.jpa.properties.hibernate.dialect", () -> "org.hibernate.dialect.PostgreSQLDialect");

    }

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void saveCustomer() {
        //GIVEN
        CustomerEntity newEntity = new CustomerEntity();
        Profile profile = new Profile();
        profile.setEmail("deneme@deneme.com");
        newEntity.setProfile(profile);
        //WHEN
        customerRepository.save(newEntity);
        //THEN
        assertThat(customerRepository.count()).isEqualTo(4);
        assertThat(customerRepository.findById(newEntity.getId())).isPresent();
    }


}
