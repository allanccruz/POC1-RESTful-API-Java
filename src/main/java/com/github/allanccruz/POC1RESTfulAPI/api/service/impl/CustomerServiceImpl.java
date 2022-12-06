package com.github.allanccruz.POC1RESTfulAPI.api.service.impl;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.CustomerRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.AddressResponseDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.CustomerResponseDto;
import com.github.allanccruz.POC1RESTfulAPI.api.entities.Customer;
import com.github.allanccruz.POC1RESTfulAPI.api.repository.CustomerRepository;
import com.github.allanccruz.POC1RESTfulAPI.api.service.CustomerService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final ModelMapper mapper;

    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto create(CustomerRequestDto customerRequestDto) {
        Customer customer = customerRepository.save(mapper.map(customerRequestDto, Customer.class));
        return mapper.map(customer, CustomerResponseDto.class);
    }

    @Override
    public CustomerResponseDto getById(UUID id) {
        return mapper.map(customerRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found!")), CustomerResponseDto.class);
    }

    @Override
    public List<CustomerResponseDto> findAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer -> mapper.map(customer, CustomerResponseDto.class))
                .toList();
    }

    @Override
    public List<AddressResponseDto> getAllAddresses(UUID id) {

        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found!"));

        return customer
                .getAddresses()
                .stream()
                .map(address -> mapper.map(address, AddressResponseDto.class))
                .toList();
    }

    @Override
    public void deleteById(UUID id) {
        Customer customer = mapper.map(getById(id), Customer.class);
        customerRepository.deleteById(customer.getId());
    }
}