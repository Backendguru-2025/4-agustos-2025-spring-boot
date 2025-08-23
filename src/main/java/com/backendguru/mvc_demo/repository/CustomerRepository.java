package com.backendguru.mvc_demo.repository;

import com.backendguru.mvc_demo.model.entity.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>  {

    Page<CustomerEntity> findByProfileEmailAndReviewsEmptyAndProfileEmailContains(Pageable pageable, String email, String matchWith);

    //CustomerEntity save(Pageable pageable, CustomerEntity customerEntity);
}