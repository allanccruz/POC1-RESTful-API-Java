package com.github.allanccruz.POC1RESTfulAPI.api.dto.request;

import com.github.allanccruz.POC1RESTfulAPI.api.enums.DocumentType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomerRequestDto {

    private String name;

    private String email;

    private String document;

    private DocumentType documentType;

    private String phoneNumber;

}