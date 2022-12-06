package com.github.allanccruz.POC1RESTfulAPI.api.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateAddressRequestDto {

    private String city;

    private String neighborhood;

    private String addressNumber;

    private String complement;

    private String cep;

}
