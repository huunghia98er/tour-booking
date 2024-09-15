package org.tour_booking.auth_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.tour_booking.auth_service.constant.PERMISSION;
import org.tour_booking.auth_service.model.request.AccountCreationRequest;
import org.tour_booking.auth_service.model.response.AccountResponse;
import org.tour_booking.auth_service.model.response.PermissionResponse;
import org.tour_booking.auth_service.service.AccountService;

import java.util.HashSet;
import java.util.List;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/11/2024
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/test.yml")
public class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AccountService accountService;

    private AccountCreationRequest accountCreationRequest;
    private AccountResponse accountResponse;

    @BeforeEach
    void initData() {
        accountCreationRequest = AccountCreationRequest.builder()
                .username("usernameTest")
                .password("passwordTest")
                .email("emailTest@tour.com")
                .phone("phoneTest")
                .build();

        accountResponse = AccountResponse.builder()
                .id(1L)
                .username("usernameTest")
                .email("emailTest@tour.com")
                .phone("phoneTest")
                .permissions(new HashSet<>(List.of(PermissionResponse.builder()
                        .id(8L)
                        .name(PERMISSION.CUSTOMER.val)
                        .build())))
                .build();
    }

    @Test
    void createAccount_customer_success() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(accountCreationRequest);

        Mockito.when(accountService.createAccount(accountCreationRequest, false))
                .thenReturn(accountResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("result.id").value(1L));
    }

}
