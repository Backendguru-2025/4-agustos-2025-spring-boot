package com.backendguru.mvc_demo;

import com.backendguru.mvc_demo.model.domain.Customer;
import com.backendguru.mvc_demo.model.dto.UpdateCustomerRequest;
import com.backendguru.mvc_demo.model.entity.CustomerEntity;
import com.backendguru.mvc_demo.model.entity.Profile;
import com.backendguru.mvc_demo.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RealCustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private RealCustomerService customerService;

    @Test
    void updateEmailForNewNonGmailEmailAddressesForCurrentEmailFromGmail() {
        //GIVEN
        CustomerEntity entity = getCustomerEntity();
        when(customerRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(customerRepository.save(entity)).thenReturn(entity);
        //WHEN
        Customer result = customerService.update(1, new UpdateCustomerRequest("test@test.com"));
        //THEN
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getEmail()).isEqualTo("test@test.com");
    }


    @Test
    void updateEmailForNewGmailEmailAddressesForCurrentEmailFromGmail() {
        //GIVEN
        CustomerEntity entity = getCustomerEntity();
        when(customerRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(customerRepository.save(entity)).thenReturn(entity);
        //WHEN
        Customer result = customerService.update(1, new UpdateCustomerRequest("test-2@gmail.com"));
        //THEN
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getEmail()).isEqualTo("test-2@yahoo.com");
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        //GIVEN
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        //WHEN & THEN
        assertThatThrownBy( () -> customerService.read(1))
        .isInstanceOf(EntityNotFoundException.class)
        .hasMessage("Customer with id 1 not found");
    }

    private static CustomerEntity getCustomerEntity() {
        CustomerEntity entity = new CustomerEntity();
        entity.setId(1L);
        Profile profile = new Profile();
        profile.setEmail("test@gmail.com");
        entity.setProfile(profile);
        return entity;
    }
}
