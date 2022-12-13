package com.github.allanccruz.POC1RESTfulAPI.api.util;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.AddressRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.entities.CustomerAddress;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AddressMapperUtil {

    private final ModelMapper mapper;

    public void addressRequestDtoToCustomerAddress() {
        TypeMap<AddressRequestDto, CustomerAddress> propertyMapper = mapper.createTypeMap(AddressRequestDto.class, CustomerAddress.class);
        propertyMapper.addMapping(AddressRequestDto::getLocalidade, CustomerAddress::setCity);
        propertyMapper.addMapping(AddressRequestDto::getBairro, CustomerAddress::setNeighborhood);
        propertyMapper.addMapping(AddressRequestDto::getLogradouro, CustomerAddress::setAddress);
        propertyMapper.addMapping(AddressRequestDto::getNumero, CustomerAddress::setNumber);
        propertyMapper.addMapping(AddressRequestDto::getComplemento, CustomerAddress::setComplement);
        propertyMapper.addMapping(AddressRequestDto::getCep, CustomerAddress::setZipcode);
    }

}
