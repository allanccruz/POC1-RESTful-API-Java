package com.github.allanccruz.POC1RESTfulAPI;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.allanccruz.POC1RESTfulAPI.api.controller.CustomerController;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.request.CustomerRequestDto;
import com.github.allanccruz.POC1RESTfulAPI.api.dto.response.CustomerResponseDto;
import com.github.allanccruz.POC1RESTfulAPI.api.enums.PersonType;
import com.github.allanccruz.POC1RESTfulAPI.api.service.impl.CustomerServiceImpl;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CustomerController.class)
public class CustomerControllerTest {

    static String API = "/api/poc1/customers";

    @Autowired
    MockMvc mvc;

    @MockBean
    CustomerServiceImpl customerService;

    @MockBean
    ModelMapper modelMapper;

    @Test
    @DisplayName("Must create a customer successfully.")
    void createPfCustomerTest() throws Exception {

        CustomerRequestDto customerRequestDto = CustomerRequestDto.builder()
                .name("Allan")
                .email("allan@gmail.com")
                .document("94471339087")
                .phoneNumber("19996873544")
                .personType(PersonType.PF)
                .build();

        CustomerResponseDto customerResponseDto = CustomerResponseDto.builder()
                .id(UUID.randomUUID())
                .name("Allan")
                .email("allan@gmail.com")
                .phoneNumber("19996873544")
                .personType(PersonType.PF)
                .addresses(new ArrayList<>())
                .build();

        when(customerService.create(Mockito.any(CustomerRequestDto.class))).thenReturn(customerResponseDto);


        String requestBody = new ObjectMapper().writeValueAsString(customerRequestDto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody);

        mvc
                .perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").value(customerResponseDto.getId().toString()))
                .andExpect(jsonPath("name").value(customerResponseDto.getName()))
                .andExpect(jsonPath("email").value(customerResponseDto.getEmail()))
                .andExpect(jsonPath("phoneNumber").value(customerResponseDto.getPhoneNumber()))
                .andExpect(jsonPath("personType").value(customerResponseDto.getPersonType().toString()))
                .andExpect(jsonPath("addresses").isEmpty());

    }

    @Test
    @DisplayName("Must not create a customer and throw an exception.")
    public void createInvalidCustomerTest() {


    }
}
