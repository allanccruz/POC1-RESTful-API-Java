package com.github.allanccruz.POC1RESTfulAPI.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateAddressRequestDto {

    private String city;

    private String neighborhood;

    private String addressNumber;

    private String complement;

    private String cep;

    private Boolean mainAddress;

}
