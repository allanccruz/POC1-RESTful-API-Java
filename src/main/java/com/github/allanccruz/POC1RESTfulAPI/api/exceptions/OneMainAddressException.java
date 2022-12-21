package com.github.allanccruz.POC1RESTfulAPI.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OneMainAddressException extends RuntimeException {

    private String message;

    private String errorCode;

}
