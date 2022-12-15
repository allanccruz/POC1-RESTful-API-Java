package com.github.allanccruz.POC1RESTfulAPI.api.controller;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.AddressRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.AddressResponseDto;
import com.github.allanccruz.POC1RESTfulAPI.api.service.AddressService;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/poc1/addresses")
public class AddressController {

    private final ModelMapper mapper;

    private final AddressService addressService;

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponseDto createAddress(@RequestBody AddressRequestDto addressRequestDto) {
        return mapper.map(addressService.create(addressRequestDto), AddressResponseDto.class);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponseDto getAddressById(@PathVariable UUID id) {
        return mapper.map(addressService.getById(id), AddressResponseDto.class);
    }

    @Transactional
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponseDto updateAddress(@PathVariable UUID id, @RequestBody AddressRequestDto addressRequestDto) {
        return mapper.map(addressService.update(id, addressRequestDto), AddressResponseDto.class);
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable UUID id) {
        addressService.delete(id);
    }

}
