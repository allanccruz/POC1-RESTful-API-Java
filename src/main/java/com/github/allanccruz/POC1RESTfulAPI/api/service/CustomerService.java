package com.github.allanccruz.POC1RESTfulAPI.api.service;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.CustomerRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.UpdateCustomerRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.AddressResponseDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.CustomerResponseDto;
import com.github.allanccruz.POC1RESTfulAPI.api.entities.Customer;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    CustomerResponseDto create(CustomerRequestDto customerRequestDto);

    CustomerResponseDto getById(UUID customerId);

    Page<CustomerResponseDto> getAllCustomers(Pageable pageable);

    Page<CustomerResponseDto> getCustomersByName(String name, Pageable pageable);

    List<AddressResponseDto> getAllAddresses(UUID customerId);

    void deleteById(UUID customerId);

    CustomerResponseDto update(UUID customerId, UpdateCustomerRequestDto updateCustomerRequestDto);

    Customer findCustomerById(UUID customerId);

}
