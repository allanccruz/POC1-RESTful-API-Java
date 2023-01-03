package com.github.allanccruz.POC1RESTfulAPI.api.dto.request;

import com.github.allanccruz.POC1RESTfulAPI.api.enums.PersonType;
import com.github.allanccruz.POC1RESTfulAPI.api.validation.document.CnpjGroup;
import com.github.allanccruz.POC1RESTfulAPI.api.validation.document.CpfGroup;
import com.github.allanccruz.POC1RESTfulAPI.api.validation.document.CustomerGroupSequenceProvider;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

@Getter
@Setter
@Builder
@AllArgsConstructor
@GroupSequenceProvider(CustomerGroupSequenceProvider.class)
public class CustomerRequestDto {

    private UUID id;

    @NotBlank(message = "Name field cannot be blank")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email field cannot be blank")
    private String email;

    @CPF(groups = CpfGroup.class)
    @CNPJ(groups = CnpjGroup.class)
    @NotBlank(message = "Document field cannot be blank")
    private String document;

    @NotNull(message = "Person-type field cannot be blank")
    private PersonType personType;

    @NotBlank(message = "Phone number field cannot be blank")
    private String phoneNumber;

}