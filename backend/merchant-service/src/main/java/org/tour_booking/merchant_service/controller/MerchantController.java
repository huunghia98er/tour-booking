package org.tour_booking.merchant_service.controller;

import api.ApiResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.tour_booking.merchant_service.model.request.AdminListMerchantRequest;
import org.tour_booking.merchant_service.model.request.ApproveMerchantRequest;
import org.tour_booking.merchant_service.model.request.CreateMerchantActorRequest;
import org.tour_booking.merchant_service.model.request.RegisterMerchantRequest;
import org.tour_booking.merchant_service.model.response.AdminListMerchantResponse;
import org.tour_booking.merchant_service.model.response.ApproveMerchantResponse;
import org.tour_booking.merchant_service.model.response.CreateMerchantActorResponse;
import org.tour_booking.merchant_service.model.response.RegisterMerchantResponse;
import org.tour_booking.merchant_service.service.MerchantService;
import utils.SimplePage;

@RestController
@RequestMapping("/merchant")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MerchantController {

    MerchantService merchantService;

    /**
     * Register Merchant
     */
    @PostMapping("/register")
    ApiResponse<RegisterMerchantResponse> registerMerchant(@RequestBody @Valid RegisterMerchantRequest request) {
        var response = merchantService.register(request);
        return ApiResponse.<RegisterMerchantResponse>builder()
                .data(response)
                .build();
    }

    /**
     * Approve Merchant
     */
    @PutMapping("/approve")
    ApiResponse<ApproveMerchantResponse> approveMerchant(@RequestBody @Valid ApproveMerchantRequest request) {
        var response = merchantService.approve(request);
        return ApiResponse.<ApproveMerchantResponse>builder()
                .data(response)
                .build();
    }

    /**
     * Create Merchant's Actors
     */
    @PostMapping("/actors/create")
    ApiResponse<CreateMerchantActorResponse> createActors(@RequestBody @Valid CreateMerchantActorRequest request) {
        var response = merchantService.createActor(request);
        return ApiResponse.<CreateMerchantActorResponse>builder()
                .data(response)
                .build();
    }

    /**
     * Admin Page List Merchant
     */
    @GetMapping("/list")
    ApiResponse<SimplePage<AdminListMerchantResponse>> adminListMerchant
            (@RequestParam(value = "verificationStatus", required = false) String verificationStatus,
             @RequestParam(value = "isActive", required = false)  Boolean isActive,
             @RequestParam(value = "fromDate", required = false) String fromDate,
             @RequestParam(value = "toDate", required = false) String toDate,
             @PageableDefault(sort = "registrationDate", direction = Sort.Direction.DESC) Pageable pageable)
    {
        var request = AdminListMerchantRequest.builder()
                .verificationStatus(verificationStatus)
                .isActive(isActive)
                .fromDate(fromDate)
                .toDate(toDate)
                .pageable(pageable)
                .build();
        var response = merchantService.getAllByFilters(request);
        return ApiResponse.<SimplePage<AdminListMerchantResponse>>builder()
                .data(response)
                .build();
    }

}
