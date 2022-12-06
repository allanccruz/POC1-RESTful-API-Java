package com.github.allanccruz.POC1RESTfulAPI.api.dto.response;

import com.github.allanccruz.POC1RESTfulAPI.api.enums.DocumentType;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomerResponseDto {

    private UUID id;

    private String name;

    private String email;

    private String phoneNumber;

    private DocumentType documentType;

    private List<AddressResponseDto> addresses;

}
