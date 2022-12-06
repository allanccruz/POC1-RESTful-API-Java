package com.github.allanccruz.POC1RESTfulAPI.api.service;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.CustomerRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.UpdateCustomerRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.AddressResponseDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.CustomerResponseDto;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    CustomerResponseDto create(CustomerRequestDto customerRequestDto);

    CustomerResponseDto getById(UUID id);

    List<CustomerResponseDto> findAllCustomers();

    List<AddressResponseDto> getAllAddresses(UUID id);

    void deleteById(UUID id);

    CustomerResponseDto update(UUID id, UpdateCustomerRequestDto updateCustomerRequestDto);

}
