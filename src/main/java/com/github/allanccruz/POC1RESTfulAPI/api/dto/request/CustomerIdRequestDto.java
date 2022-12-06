package com.github.allanccruz.POC1RESTfulAPI.api.dto.request;

import java.util.UUID;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomerIdRequestDto {

    private UUID id;

}