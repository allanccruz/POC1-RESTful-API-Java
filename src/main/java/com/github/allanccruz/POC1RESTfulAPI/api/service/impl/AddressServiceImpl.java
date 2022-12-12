package com.github.allanccruz.POC1RESTfulAPI.api.service.impl;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.AddressRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.UpdateAddressRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.AddressResponseDto;
import com.github.allanccruz.POC1RESTfulAPI.api.entities.Address;
import com.github.allanccruz.POC1RESTfulAPI.api.entities.Customer;
import com.github.allanccruz.POC1RESTfulAPI.api.repository.AddressRepository;
import com.github.allanccruz.POC1RESTfulAPI.api.repository.CustomerRepository;
import com.github.allanccruz.POC1RESTfulAPI.api.service.AddressService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final ModelMapper mapper;

    private final AddressRepository addressRepository;

    private final CustomerRepository customerRepository;

    @Override
    public AddressResponseDto create(AddressRequestDto addressRequestDto) {

        Customer customer = customerRepository
                .findById(addressRequestDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found!"));

        addressRequestDto.setMainAddress(customer.getAddresses().isEmpty());

        if (customer.getAddresses().size() == 5) {
            throw new RuntimeException("Limit of addresses reached!");
        }

        Address address = addressRepository.save(mapper.map(addressRequestDto, Address.class));

        return mapper.map(address, AddressResponseDto.class);
    }

    @Override
    public AddressResponseDto getById(UUID id) {
        return mapper.map(addressRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found!")), AddressResponseDto.class);
    }

    @Override
    public AddressResponseDto update(UUID id, UpdateAddressRequestDto updateAddressRequestDto) {
        Address address = addressRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found!"));

        address.setCity(updateAddressRequestDto.getCity());
        address.setNeighborhood(updateAddressRequestDto.getNeighborhood());
        address.setAddressNumber(updateAddressRequestDto.getAddressNumber());
        address.setComplement(updateAddressRequestDto.getComplement());
        address.setCep(updateAddressRequestDto.getCep());

        addressRepository.save(address);

        return mapper.map(address, AddressResponseDto.class);
    }

    @Override
    public void delete(UUID id) {
        Address address = mapper.map(getById(id), Address.class);
        addressRepository.deleteById(address.getId());
    }
}