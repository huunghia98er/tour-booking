package org.tour_booking.auth_service.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import org.tour_booking.auth_service.model.request.AccountCreationRequest;
import org.tour_booking.auth_service.model.request.AccountUpdateRequest;
import org.tour_booking.auth_service.model.response.AccountResponse;
import org.tour_booking.auth_service.model.response.ApiResponse;
import org.tour_booking.auth_service.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {

    AccountService accountService;

    @PostMapping("/register")
    ApiResponse<AccountResponse> createAccount(@RequestBody @Valid AccountCreationRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .data(accountService.createAccount(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<AccountResponse>> getAccounts() {
        return ApiResponse.<List<AccountResponse>>builder()
                .data(accountService.getAccounts())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<AccountResponse> getAccount(@PathVariable("id") String userId) {
        return ApiResponse.<AccountResponse>builder()
                .data(accountService.getAccount(userId))
                .build();
    }

    @GetMapping("/my")
    ApiResponse<AccountResponse> getMyInfo() {
        return ApiResponse.<AccountResponse>builder()
                .data(accountService.getMyInfo())
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<?> deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
        return ApiResponse.<String>builder()
                .data("success deleted")
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<AccountResponse> updateAccount(@PathVariable String id, @RequestBody AccountUpdateRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .data(accountService.updateAccount(id, request))
                .build();
    }

}
