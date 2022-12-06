package com.github.allanccruz.POC1RESTfulAPI.api.dto.request;

import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
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
