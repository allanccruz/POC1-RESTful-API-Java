package com.github.allanccruz.POC1RESTfulAPI.api.dto.request;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddressRequestDto {

    private UUID id;

    private String city;

    private String neighborhood;

    private String addressNumber;

    private String complement;

    private String cep;

    private CustomerIdRequestDto customerIdDto;

    private Boolean mainAddress;

}
