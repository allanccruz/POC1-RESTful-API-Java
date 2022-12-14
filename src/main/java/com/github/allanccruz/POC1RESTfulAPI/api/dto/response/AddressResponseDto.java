package com.github.allanccruz.POC1RESTfulAPI.api.dto.response;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseDto {

    private UUID id;

    private String city;

    private String neighborhood;

    private String address;

    private String number;

    private String complement;

    private String zipcode;

    private UUID customerId;

    private Boolean mainAddress;

}
