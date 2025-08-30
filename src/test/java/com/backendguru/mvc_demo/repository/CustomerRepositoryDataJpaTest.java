package com.backendguru.mvc_demo.repository;

import com.backendguru.mvc_demo.model.entity.CustomerEntity;
import com.backendguru.mvc_demo.model.entity.Profile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryDataJpaTest {

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
        assertThat(
                customerRepository.findById(102L).get().getProfile().getEmail())
                .isEqualTo("deneme@deneme.com");
        assertThat(
                customerRepository.findById(1L).get().getProfile().getEmail())
                .isEqualTo("dad@adad.com");
    }

}