package com.github.allanccruz.POC1RESTfulAPI.api.service;

import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.AddressRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.entities.Address;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

    Address create (AddressRequestDto addressRequestDto);

}
