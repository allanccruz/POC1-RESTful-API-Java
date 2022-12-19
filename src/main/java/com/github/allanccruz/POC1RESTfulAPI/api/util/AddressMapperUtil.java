package com.github.allanccruz.POC1RESTfulAPI.api.util;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.AddressRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.entities.CustomerAddress;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class AddressMapperUtil {

    public void addressRequestDtoToCustomerAddress(ModelMapper mapper) {

        TypeMap<AddressRequestDto, CustomerAddress> propertyMapper = mapper.getTypeMap(AddressRequestDto.class, CustomerAddress.class);

        if (propertyMapper == null) {
            propertyMapper = mapper.createTypeMap(AddressRequestDto.class, CustomerAddress.class);
            propertyMapper.addMapping(AddressRequestDto::getLocalidade, CustomerAddress::setCity);
            propertyMapper.addMapping(AddressRequestDto::getBairro, CustomerAddress::setNeighborhood);
            propertyMapper.addMapping(AddressRequestDto::getLogradouro, CustomerAddress::setAddress);
            propertyMapper.addMapping(AddressRequestDto::getNumero, CustomerAddress::setNumber);
            propertyMapper.addMapping(AddressRequestDto::getComplemento, CustomerAddress::setComplement);
            propertyMapper.addMapping(AddressRequestDto::getCep, CustomerAddress::setZipcode);
        }

    }

}