package com.github.allanccruz.POC1RESTfulAPI.api.service;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.CustomerRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.entities.Customer;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    Customer create(CustomerRequestDto customerRequestDto);

    Customer findById(UUID id);
}
