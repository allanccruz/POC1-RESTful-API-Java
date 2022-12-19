package com.github.allanccruz.POC1RESTfulAPI.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Errors {

    PC101("PC-101", "This customer does not exists"),
    PC201("PC-102", "This address does not exists");

    private final String code;
    private final String message;

}
