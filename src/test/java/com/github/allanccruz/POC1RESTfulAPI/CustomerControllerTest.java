package com.github.allanccruz.POC1RESTfulAPI;

import static org.hamcrest.Matchers.hasSize;
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

    static CustomerRequestDto createPfCustomerRequestDto() {
        return CustomerRequestDto.builder()
                .name("Allan")
                .email("allan@gmail.com")
                .document("94471339087")
                .phoneNumber("19996873544")
                .personType(PersonType.PF)
                .build();
    }

    static CustomerResponseDto createPfCustomerResponseDto() {
        return CustomerResponseDto.builder()
                .id(UUID.randomUUID())
                .name("Allan")
                .email("allan@gmail.com")
                .phoneNumber("19996873544")
                .personType(PersonType.PF)
                .addresses(new ArrayList<>())
                .build();
    }

    static CustomerRequestDto createPjCustomerRequestDto() {
        return CustomerRequestDto.builder()
                .name("Allan")
                .email("allan@gmail.com")
                .document("33022407000179")
                .phoneNumber("19996873544")
                .personType(PersonType.PJ)
                .build();
    }

    static CustomerResponseDto createPjCustomerResponseDto() {
        return CustomerResponseDto.builder()
                .id(UUID.randomUUID())
                .name("Allan")
                .email("allan@gmail.com")
                .phoneNumber("19996873544")
                .personType(PersonType.PJ)
                .addresses(new ArrayList<>())
                .build();
    }

    @Test
    @DisplayName("Must create a PF customer successfully.")
    void successfullyCreatePfCustomerTest() throws Exception {

        CustomerRequestDto customerRequestDto = createPfCustomerRequestDto();

        CustomerResponseDto customerResponseDto = createPfCustomerResponseDto();

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
    @DisplayName("Must create a PJ customer successfully.")
    void successfullyCreatePjCustomerTest() throws Exception {

        CustomerRequestDto customerRequestDto = createPjCustomerRequestDto();

        CustomerResponseDto customerResponseDto = createPjCustomerResponseDto();

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
    @DisplayName("Must not create a customer and throw an exception with a list of errors of failed attributes validations.")
    void invalidCustomerRequestTest() throws Exception {

        CustomerRequestDto customerRequestDto = new CustomerRequestDto();

        String requestBody = new ObjectMapper().writeValueAsString(customerRequestDto);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody);

        mvc
                .perform(request)
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("httpCode").isNumber())
                .andExpect(jsonPath("message").isString())
                .andExpect(jsonPath("internalCode").isString())
                .andExpect(jsonPath("path").isString())
                .andExpect(jsonPath("timestamp").isString())
                .andExpect(jsonPath("errors").isArray())
                .andExpect(jsonPath("errors", hasSize(5)));

    }
}
