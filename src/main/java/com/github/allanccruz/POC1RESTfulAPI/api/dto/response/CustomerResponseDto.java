package com.github.allanccruz.POC1RESTfulAPI.api.dto.response;

import com.github.allanccruz.POC1RESTfulAPI.api.enums.PersonType;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerResponseDto {

    private UUID id;

    private String name;

    private String email;

    private String phoneNumber;

    private PersonType personType;

    private List<AddressResponseDto> addresses;

}
