package com.github.allanccruz.POC1RESTfulAPI.api.service.impl;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.AddressRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.entities.Address;
import com.github.allanccruz.POC1RESTfulAPI.api.entities.Customer;
import com.github.allanccruz.POC1RESTfulAPI.api.repository.AddressRepository;
import com.github.allanccruz.POC1RESTfulAPI.api.service.AddressService;
import com.github.allanccruz.POC1RESTfulAPI.api.service.CustomerService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final ModelMapper mapper;

    private final AddressRepository addressRepository;

    private final CustomerService customerService;

    @Override
    public Address create(AddressRequestDto addressRequestDto) {
        Customer customer = customerService.findById(addressRequestDto.getCustomerIdDto().getId());

        if (customer.getAddresses().isEmpty()) {
            addressRequestDto.setMainAddress(true);
        } else {
            addressRequestDto.setMainAddress(false);
        }

        return addressRepository.save(mapper.map(addressRequestDto, Address.class));
    }

    @Override
    public Address getById(UUID id) {
        return addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found!"));
    }

}