package com.github.allanccruz.POC1RESTfulAPI.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Errors {

    PC101("PC-101", "This customer does not exists."),
    PC201("PC-201", "This address does not exists."),
    PC202("PC-202", "Invalid zipcode given. Please, give a valid zipcode."),
    PC203("PC-203", "Limit of this customer addresses reached.");

    private final String code;

    private final String message;

}
