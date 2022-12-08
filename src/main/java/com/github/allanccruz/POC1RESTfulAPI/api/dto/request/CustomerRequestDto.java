package com.github.allanccruz.POC1RESTfulAPI.api.dto.request;

import com.github.allanccruz.POC1RESTfulAPI.api.enums.PersonType;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerRequestDto {

    private UUID id;

    private String name;

    private String email;

    private String document;

    private PersonType personType;

    private String phoneNumber;

}