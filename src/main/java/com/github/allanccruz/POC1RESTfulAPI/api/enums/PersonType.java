package com.github.allanccruz.POC1RESTfulAPI.api.enums;

import com.github.allanccruz.POC1RESTfulAPI.api.validation.document.CnpjGroup;
import com.github.allanccruz.POC1RESTfulAPI.api.validation.document.CpfGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PersonType {

    PF("000.000.000-00", CpfGroup.class),
    PJ("00.000.000/0000-00", CnpjGroup.class);

    private final String mask;

    private final Class<?> group;

}
