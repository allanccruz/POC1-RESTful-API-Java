package com.github.allanccruz.POC1RESTfulAPI.api.controller;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.AddressRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.CustomerRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.UpdateCustomerRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.AddressResponseDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.CustomerResponseDto;
import com.github.allanccruz.POC1RESTfulAPI.api.service.AddressService;
import com.github.allanccruz.POC1RESTfulAPI.api.service.CustomerService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/poc1/customers")
public class CustomerController {

    private final ModelMapper mapper;

    private final CustomerService customerService;

    private final AddressService addressService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponseDto createCustomer(@RequestBody @Valid CustomerRequestDto customerRequestDto) {
        return customerService.create(customerRequestDto);
    }

    @GetMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponseDto findCustomer(@PathVariable UUID customerId) {
        return customerService.getById(customerId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<CustomerResponseDto> getAllCustomers(
            @PageableDefault(size = 5, sort = {"name"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return customerService.getAllCustomers(pageable);
    }

    @GetMapping("/filter")
    public Page<CustomerResponseDto> getCustomersByName(@RequestParam String name, Pageable pageable) {
        return customerService.getCustomersByName(name, pageable);
    }

    @PutMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponseDto updateCustomer(@PathVariable UUID customerId, @RequestBody @Valid UpdateCustomerRequestDto updateCustomerRequestDto) {
        return customerService.update(customerId, updateCustomerRequestDto);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable UUID customerId) {
        customerService.deleteById(customerId);
    }

    @PostMapping("/{customerId}/addresses")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponseDto createAddress(@PathVariable UUID customerId, @RequestBody @Valid AddressRequestDto addressRequestDto) {
        return mapper.map(addressService.create(customerId, addressRequestDto), AddressResponseDto.class);
    }

    @GetMapping("/{customerId}/addresses")
    @ResponseStatus(HttpStatus.OK)
    public List<AddressResponseDto> getAllAddressesOfCustomer(@PathVariable UUID customerId) {
        return customerService.getAllAddresses(customerId);
    }

    @PutMapping("/{customerId}/addresses/{addressId}")
    @ResponseStatus(HttpStatus.OK)
    public AddressResponseDto updateAddress(@PathVariable UUID customerId, @PathVariable UUID addressId,
                                            @RequestBody @Valid AddressRequestDto addressRequestDto) {
        return mapper.map(addressService.update(customerId, addressId, addressRequestDto), AddressResponseDto.class);
    }

    @DeleteMapping("/{customerId}/addresses/{addressId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable UUID customerId, @PathVariable UUID addressId) {
        addressService.delete(customerId, addressId);
    }
}
