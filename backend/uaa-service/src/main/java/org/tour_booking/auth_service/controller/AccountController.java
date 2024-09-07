package org.tour_booking.auth_service.controller;

import api.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import org.tour_booking.auth_service.model.request.AccountCreationRequest;
import org.tour_booking.auth_service.model.request.AccountUpdateRequest;
import org.tour_booking.auth_service.model.request.ConfirmAccountRequest;
import org.tour_booking.auth_service.model.request.CreateMerchantActorRequest;
import org.tour_booking.auth_service.model.response.AccountResponse;
import org.tour_booking.auth_service.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {

    AccountService accountService;

    @PostMapping("/user/register")
    ApiResponse<AccountResponse> createAccountCustomer(@RequestBody @Valid AccountCreationRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .data(accountService.createAccount(request, false))
                .build();
    }

    /**
     * Admin Role only
     */
    @PostMapping("/merchant/create")
    ApiResponse<AccountResponse> createAccountMerchant(@RequestBody @Valid AccountCreationRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .data(accountService.createAccount(request, true))
                .build();
    }

    /**
     * Merchant Role only
     */
    @PostMapping("/merchant/actors/create")
    ApiResponse<List<AccountResponse>> createMerchantActorAccount(@RequestBody @Valid CreateMerchantActorRequest request) {
        return ApiResponse.<List<AccountResponse>>builder()
                .data(accountService.createMerchantActorAccount(request))
                .build();
    }

    @PostMapping("/confirm/{token}")
    ApiResponse<Object> userConfirm(@PathVariable("token") String token) {
        var request = ConfirmAccountRequest.builder()
                .token(token)
                .build();
        return ApiResponse.builder()
                .data(accountService.confirmAccountToActive(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<AccountResponse>> getAccounts(HttpServletRequest servletRequest) {
        return ApiResponse.<List<AccountResponse>>builder()
                .data(accountService.getAccounts(servletRequest))
                .build();
    }

    @GetMapping("/my")
    ApiResponse<AccountResponse> getMyInfo() {
        return ApiResponse.<AccountResponse>builder()
                .data(accountService.getMyInfo())
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<?> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ApiResponse.<String>builder()
                .data("success deleted")
                .build();
    }

    @PutMapping("/user/update")
    ApiResponse<AccountResponse> updateAccount(HttpServletRequest servletRequest, @RequestBody AccountUpdateRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .data(accountService.updateAccount(servletRequest, request))
                .build();
    }

}
