package com.github.allanccruz.POC1RESTfulAPI.api.controller;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.CustomerRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.UpdateCustomerRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.AddressResponseDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.CustomerResponseDto;
import com.github.allanccruz.POC1RESTfulAPI.api.service.CustomerService;
import jakarta.transaction.Transactional;
import java.util.List;
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
@RequestMapping("api/poc1/customers")
public class CustomerController {

    private final ModelMapper mapper;

    private final CustomerService customerService;

    @PostMapping
    @Transactional
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.create(customerRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> findCustomer(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAllCustomers());
    }

    @GetMapping("/{id}/addresses")
    public ResponseEntity<List<AddressResponseDto>> getAllAddressesOfCustomer(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllAddresses(id));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable UUID id, @RequestBody UpdateCustomerRequestDto updateCustomerRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.update(id, updateCustomerRequestDto));
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable UUID id) {
        customerService.deleteById(id);
    }

}
