package com.github.allanccruz.POC1RESTfulAPI.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldErrorResponse {

    private String message;

    private String field;

}
