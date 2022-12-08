package com.github.allanccruz.POC1RESTfulAPI.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateCustomerRequestDto {

    private String name;

    private String email;

    private String phoneNumber;

}
