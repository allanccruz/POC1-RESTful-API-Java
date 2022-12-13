package com.github.allanccruz.POC1RESTfulAPI.api.validation.document;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.CustomerRequestDto;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

public class CustomerGroupSequenceProvider implements DefaultGroupSequenceProvider<CustomerRequestDto> {

    @Override
    public List<Class<?>> getValidationGroups(CustomerRequestDto customerRequestDto) {
        List<Class<?>> groups = new ArrayList<>();
        groups.add(CustomerRequestDto.class);

        if (isSelectedPerson(customerRequestDto)) {
            groups.add(customerRequestDto.getPersonType().getGroup());
        }

        return groups;
    }

    protected boolean isSelectedPerson(CustomerRequestDto customerRequestDto) {
        return customerRequestDto != null && customerRequestDto.getPersonType() != null;
    }

}
