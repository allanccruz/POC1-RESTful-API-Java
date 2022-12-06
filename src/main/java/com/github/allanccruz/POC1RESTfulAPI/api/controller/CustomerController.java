package com.github.allanccruz.POC1RESTfulAPI.api.controller;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.CustomerRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.CustomerResponseDto;
import com.github.allanccruz.POC1RESTfulAPI.api.service.CustomerService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/poc1/customers")
public class CustomerController {

    private final ModelMapper mapper;

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(customerService.create(customerRequestDto), CustomerResponseDto.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> findCustomer(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(mapper.map(customerService.findById(id), CustomerResponseDto.class));
    }

}
