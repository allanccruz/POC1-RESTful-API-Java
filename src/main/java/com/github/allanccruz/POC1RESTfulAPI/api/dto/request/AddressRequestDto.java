package com.github.allanccruz.POC1RESTfulAPI.api.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddressRequestDto {

    @JsonIgnore
    private String localidade;

    @JsonIgnore
    private String bairro;

    @JsonIgnore
    private String logradouro;

    @NotBlank(message = "Number field cannot be blank")
    private String numero;

    private String complemento;

    @NotBlank(message = "Zipcode field cannot be blank")
    private String cep;

    @JsonIgnore
    private UUID customerId;

    private Boolean mainAddress;

}
