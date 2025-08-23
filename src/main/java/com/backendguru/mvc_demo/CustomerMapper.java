package com.backendguru.mvc_demo;

import com.backendguru.mvc_demo.model.domain.Customer;
import com.backendguru.mvc_demo.model.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "profile.name", source = "name")
    @Mapping(target = "profile.email", source = "email")
    CustomerEntity toEntity(Customer customer);

    @Mapping(target = "name", source = "profile.name")
    @Mapping(target = "email", source = "profile.email")
    Customer toDomain(CustomerEntity customerEntity);
}
