package com.github.allanccruz.POC1RESTfulAPI.api.service.impl;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.AddressRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.AddressResponseDto;
import com.github.allanccruz.POC1RESTfulAPI.api.entities.CustomerAddress;
import com.github.allanccruz.POC1RESTfulAPI.api.entities.Customer;
import com.github.allanccruz.POC1RESTfulAPI.api.repository.AddressRepository;
import com.github.allanccruz.POC1RESTfulAPI.api.repository.CustomerRepository;
import com.github.allanccruz.POC1RESTfulAPI.api.service.AddressService;
import com.github.allanccruz.POC1RESTfulAPI.api.util.AddressMapperUtil;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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

    private final AddressMapperUtil addressMapperSetup;

    @Override
    public AddressResponseDto create(AddressRequestDto addressRequestDto) {

        addressMapperSetup.addressRequestDtoToCustomerAddress();

        zipCodeValidation(addressRequestDto);

        Customer customer = existCustomerById(addressRequestDto);

        settingMainAddress(addressRequestDto, customer);

        limitOfAddressesValidation(customer);

        CustomerAddress customerAddress = addressRepository.save(mapper.map(addressRequestDto, CustomerAddress.class));

        return mapper.map(customerAddress, AddressResponseDto.class);
    }

    @Override
    public AddressResponseDto getById(UUID id) {
        return mapper.map(addressRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found!")), AddressResponseDto.class);
    }

    @Override
    public AddressResponseDto update(UUID id, AddressRequestDto addressRequestDto) {
        CustomerAddress customerAddress = existAddressById(id);

        zipCodeValidation(addressRequestDto);

        settingNewAddressAtributes(addressRequestDto, customerAddress);

        ensuringOneMainAddressAtATime(addressRequestDto, customerAddress);

        addressRepository.save(customerAddress);

        return mapper.map(customerAddress, AddressResponseDto.class);
    }

    @Override
    public void delete(UUID id) {
        CustomerAddress customerAddress = mapper.map(getById(id), CustomerAddress.class);
        addressRepository.deleteById(customerAddress.getId());
    }

    private static void zipCodeValidation(AddressRequestDto addressRequestDto) {
        try {

            URL url = new URL("https://viacep.com.br/ws/" + addressRequestDto.getCep() + "/json/");
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

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
            throw new RuntimeException("CEP inválido!");
        }
    }

    private static void limitOfAddressesValidation(Customer customer) {
        if (customer.getCustomerAddresses().size() == 5) {
            throw new RuntimeException("Limit of addresses reached!");
        }
    }

    private Customer existCustomerById(AddressRequestDto addressRequestDto) {
       return customerRepository
                .findById(addressRequestDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found!"));
    }

    private static void settingMainAddress(AddressRequestDto addressRequestDto, Customer customer) {
        addressRequestDto.setMainAddress(customer.getCustomerAddresses().isEmpty());
    }

    private CustomerAddress existAddressById(UUID id) {
        return addressRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found!"));
    }

    private static void settingNewAddressAtributes(AddressRequestDto addressRequestDto, CustomerAddress customerAddress) {
        customerAddress.setZipcode(addressRequestDto.getCep());
        customerAddress.setCity(addressRequestDto.getLocalidade());
        customerAddress.setNeighborhood(addressRequestDto.getBairro());
        customerAddress.setAddress(addressRequestDto.getLogradouro());
        customerAddress.setNumber(addressRequestDto.getNumero());
        customerAddress.setComplement(addressRequestDto.getComplemento());
    }

    private static void ensuringOneMainAddressAtATime(AddressRequestDto addressRequestDto, CustomerAddress customerAddress) {
        if (Boolean.TRUE.equals(addressRequestDto.getMainAddress())) {
            customerAddress.getCustomer().getCustomerAddresses()
                    .stream()
                    .forEach(addr -> addr.setMainAddress(false));

            customerAddress.setMainAddress(true);
        } else if (Boolean.TRUE.equals(customerAddress.getMainAddress())) {
            throw new RuntimeException("You must have at least one main address!");
        }
    }


}