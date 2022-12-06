package com.github.allanccruz.POC1RESTfulAPI.api.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateCustomerRequestDto {

    private String name;

    private String email;

    private String phoneNumber;

}
