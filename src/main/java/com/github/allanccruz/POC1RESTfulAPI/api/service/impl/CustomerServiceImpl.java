package com.github.allanccruz.POC1RESTfulAPI.api.service.impl;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.CustomerRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.UpdateCustomerRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.AddressResponseDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.CustomerResponseDto;
import com.github.allanccruz.POC1RESTfulAPI.api.entities.Customer;
import com.github.allanccruz.POC1RESTfulAPI.api.enums.Errors;
import com.github.allanccruz.POC1RESTfulAPI.api.exceptions.NotFoundException;
import com.github.allanccruz.POC1RESTfulAPI.api.repository.CustomerRepository;
import com.github.allanccruz.POC1RESTfulAPI.api.service.CustomerService;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
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
                .orElseThrow(() -> new NotFoundException(Errors.PC101.getMessage(), Errors.PC101.getCode())), CustomerResponseDto.class);
    }

    @Override
    public Page<CustomerResponseDto> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(customer -> mapper.map(customer, CustomerResponseDto.class));
    }

    @Override
    public Page<CustomerResponseDto> getCustomersByName(String name, Pageable pageable) {
        return customerRepository.findByNameContains(name, pageable)
                .map(customer -> mapper.map(customer, CustomerResponseDto.class));
    }

    @Override
    public List<AddressResponseDto> getAllAddresses(UUID id) {

        Customer customer = mapper.map(getById(id), Customer.class);

        return customer
                .getCustomerAddresses()
                .stream()
                .map(address -> mapper.map(address, AddressResponseDto.class))
                .toList();
    }

    @Override
    public CustomerResponseDto update(UUID id, UpdateCustomerRequestDto updateCustomerRequestDto) {
        Customer customer = mapper.map(getById(id), Customer.class);

        settingNewCustomerAtributes(updateCustomerRequestDto, customer);

        customerRepository.save(customer);
        return mapper.map(customer, CustomerResponseDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        Customer customer = mapper.map(getById(id), Customer.class);
        customerRepository.deleteById(customer.getId());
    }

    private static void settingNewCustomerAtributes(UpdateCustomerRequestDto updateCustomerRequestDto, Customer customer) {
        customer.setName(updateCustomerRequestDto.getName());
        customer.setEmail(updateCustomerRequestDto.getEmail());
        customer.setPhoneNumber(updateCustomerRequestDto.getPhoneNumber());
    }
}