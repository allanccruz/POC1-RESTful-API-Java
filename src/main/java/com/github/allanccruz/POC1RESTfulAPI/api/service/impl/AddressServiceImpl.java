package com.github.allanccruz.POC1RESTfulAPI.api.service.impl;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.AddressRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.AddressResponseDto;
import com.github.allanccruz.POC1RESTfulAPI.api.entities.Customer;
import com.github.allanccruz.POC1RESTfulAPI.api.entities.CustomerAddress;
import com.github.allanccruz.POC1RESTfulAPI.api.enums.Errors;
import com.github.allanccruz.POC1RESTfulAPI.api.exceptions.InvalidZipcodeException;
import com.github.allanccruz.POC1RESTfulAPI.api.exceptions.LimitOfAddressesException;
import com.github.allanccruz.POC1RESTfulAPI.api.exceptions.NotFoundException;
import com.github.allanccruz.POC1RESTfulAPI.api.exceptions.OneMainAddressException;
import com.github.allanccruz.POC1RESTfulAPI.api.repository.AddressRepository;
import com.github.allanccruz.POC1RESTfulAPI.api.service.AddressService;
import com.github.allanccruz.POC1RESTfulAPI.api.service.CustomerService;
import com.github.allanccruz.POC1RESTfulAPI.api.util.AddressMapperUtil;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final ModelMapper mapper;

    private final AddressRepository addressRepository;

    private final CustomerService customerService;

    private final AddressMapperUtil addressMapperSetup;

    @Override
    @Transactional
    public AddressResponseDto create(UUID customerId, AddressRequestDto addressRequestDto) {

        addressMapperSetup.addressRequestDtoToCustomerAddress(mapper);

        zipCodeValidation(addressRequestDto);

        Customer customer = customerService.findCustomerById(customerId);

        settingMainAddress(addressRequestDto, customer);

        limitOfAddressesValidation(customer);

        addressRequestDto.setCustomerId(customerId);

        CustomerAddress customerAddress = addressRepository.save(mapper.map(addressRequestDto, CustomerAddress.class));

        return mapper.map(customerAddress, AddressResponseDto.class);
    }

    @Override
    public AddressResponseDto getById(UUID addressId) {
        return mapper.map(findAddressById(addressId), AddressResponseDto.class);
    }

    @Override
    @Transactional
    public AddressResponseDto update(UUID customerId, UUID addressId, AddressRequestDto addressRequestDto) {
        Customer customer = customerService.findCustomerById(customerId);

        CustomerAddress customerAddress = findAddressById(addressId);

        zipCodeValidation(addressRequestDto);

        settingNewAddressAtributes(addressRequestDto, customerAddress);

        ensuringOneMainAddressAtATime(addressRequestDto, customer, customerAddress);

        addressRepository.save(customerAddress);

        return mapper.map(customerAddress, AddressResponseDto.class);
    }

    @Override
    public void delete(UUID customerId, UUID addressId) {
        customerService.findCustomerById(customerId);
        CustomerAddress address = findAddressById(addressId);
        addressRepository.deleteById(address.getId());
    }

    private CustomerAddress findAddressById(UUID addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new NotFoundException(Errors.PC201.getMessage(), Errors.PC201.getCode()));
    }

    private void zipCodeValidation(AddressRequestDto addressRequestDto) {
        try {

            URL url = new URL("https://viacep.com.br/ws/" + addressRequestDto.getCep() + "/json/");
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            String line = "";
            StringBuilder jsonCep = new StringBuilder();

            while ((line = br.readLine()) != null) {
                jsonCep.append(line);
            }

            AddressRequestDto addressRequestDtoAux = new Gson().fromJson(jsonCep.toString(), AddressRequestDto.class);

            addressRequestDto.setLocalidade(addressRequestDtoAux.getLocalidade());
            addressRequestDto.setBairro(addressRequestDtoAux.getBairro());
            addressRequestDto.setLogradouro(addressRequestDtoAux.getLogradouro());

        } catch (Exception e) {
            throw new InvalidZipcodeException(Errors.PC202.getMessage(), Errors.PC202.getCode());
        }
    }

    private void limitOfAddressesValidation(Customer customer) {
        if (customer.getCustomerAddresses().size() == 5) {
            throw new LimitOfAddressesException(Errors.PC203.getMessage(), Errors.PC203.getCode());
        }
    }

    private void settingMainAddress(AddressRequestDto addressRequestDto, Customer customer) {
        addressRequestDto.setMainAddress(customer.getCustomerAddresses().isEmpty());
    }

    private void settingNewAddressAtributes(AddressRequestDto addressRequestDto, CustomerAddress customerAddress) {
        customerAddress.setZipcode(addressRequestDto.getCep());
        customerAddress.setCity(addressRequestDto.getLocalidade());
        customerAddress.setNeighborhood(addressRequestDto.getBairro());
        customerAddress.setAddress(addressRequestDto.getLogradouro());
        customerAddress.setNumber(addressRequestDto.getNumero());
        customerAddress.setComplement(addressRequestDto.getComplemento());
    }

    private void ensuringOneMainAddressAtATime(AddressRequestDto addressRequestDto, Customer customer, CustomerAddress customerAddress) {
        if (Boolean.TRUE.equals(addressRequestDto.getMainAddress())) {
            customer.getCustomerAddresses()
                    .forEach(addr -> addr.setMainAddress(false));

            customerAddress.setMainAddress(true);
        } else if (Boolean.TRUE.equals(customerAddress.getMainAddress())) {
            throw new OneMainAddressException(Errors.PC204.getMessage(), Errors.PC204.getCode());
        }
    }
}