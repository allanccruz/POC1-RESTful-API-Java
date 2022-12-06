package com.github.allanccruz.POC1RESTfulAPI.api.controller;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.AddressRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.UpdateAddressRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.AddressResponseDto;
import com.github.allanccruz.POC1RESTfulAPI.api.service.AddressService;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/poc1/addresses")
public class AddressController {

    private final ModelMapper mapper;

    private final AddressService addressService;

    @PostMapping
    @Transactional
    public ResponseEntity<AddressResponseDto> createAddress(@RequestBody AddressRequestDto addressRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(addressService.create(addressRequestDto), AddressResponseDto.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDto> getAddressById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(addressService.getById(id), AddressResponseDto.class));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDto> updateAddress(@PathVariable UUID id, @RequestBody UpdateAddressRequestDto updateAddressRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(addressService.update(id, updateAddressRequestDto), AddressResponseDto.class));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deleteAddress(@PathVariable UUID id) {
        addressService.delete(id);
        ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
