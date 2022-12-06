package com.github.allanccruz.POC1RESTfulAPI.api.service.impl;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.AddressRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.AddressResponseDto;
import com.github.allanccruz.POC1RESTfulAPI.api.entities.Address;
import com.github.allanccruz.POC1RESTfulAPI.api.entities.Customer;
import com.github.allanccruz.POC1RESTfulAPI.api.repository.AddressRepository;
import com.github.allanccruz.POC1RESTfulAPI.api.repository.CustomerRepository;
import com.github.allanccruz.POC1RESTfulAPI.api.service.AddressService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final ModelMapper mapper;

    private final AddressRepository addressRepository;

    private final CustomerRepository customerRepository;

    @Override
    public AddressResponseDto create(AddressRequestDto addressRequestDto) {

        Customer customer = customerRepository
                .findById(addressRequestDto.getCustomerIdDto().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found!"));

        addressRequestDto.setMainAddress(customer.getAddresses().isEmpty());
        addressRepository.save(mapper.map(addressRequestDto, Address.class));

        return mapper.map(addressRequestDto, AddressResponseDto.class);
    }

    @Override
    public AddressResponseDto getById(UUID id) {
        return mapper.map(addressRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found!")), AddressResponseDto.class);
    }

}