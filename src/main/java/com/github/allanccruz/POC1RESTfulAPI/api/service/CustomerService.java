package com.github.allanccruz.POC1RESTfulAPI.api.service;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.CustomerRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.CustomerResponseDto;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    CustomerResponseDto create(CustomerRequestDto customerRequestDto);

    CustomerResponseDto findById(UUID id);

    List<CustomerResponseDto> findAllCustomers();
}
