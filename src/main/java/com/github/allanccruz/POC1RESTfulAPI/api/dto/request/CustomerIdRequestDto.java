package com.github.allanccruz.POC1RESTfulAPI.api.dto.request;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerIdRequestDto {

    private UUID id;

}