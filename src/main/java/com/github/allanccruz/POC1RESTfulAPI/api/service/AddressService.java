package com.github.allanccruz.POC1RESTfulAPI.api.service;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.AddressRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.AddressResponseDto;
import com.github.allanccruz.POC1RESTfulAPI.api.entities.CustomerAddress;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

    AddressResponseDto create(UUID customerId, AddressRequestDto addressRequestDto);

    AddressResponseDto getById(UUID addressId);

    void delete(UUID customerId, UUID addressId);

    AddressResponseDto update(UUID customerId, UUID addressId, AddressRequestDto addressRequestDto);

    CustomerAddress findAddressById(UUID addressId);
}
