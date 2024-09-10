package org.tour_booking.merchant_service.controller;

import api.ApiResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.tour_booking.merchant_service.model.request.BusinessPolicyCreationRequest;
import org.tour_booking.merchant_service.model.request.BusinessPolicyUpdateRequest;
import org.tour_booking.merchant_service.model.response.BusinessPolicyDTO;
import org.tour_booking.merchant_service.service.PolicyService;
import utils.SimplePage;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/10/2024
 */

@RestController
@RequestMapping("/policy")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BusinessPolicyController {

    PolicyService policyService;

    /**
     * Create Policy
     */
    @PostMapping("/create")
    ApiResponse<BusinessPolicyDTO> create(@RequestBody @Valid BusinessPolicyCreationRequest request) {
        var response = policyService.create(request);
        return ApiResponse.<BusinessPolicyDTO>builder()
                .data(response)
                .build();
    }

    /**
     * Update Policy
     */
    @PutMapping("/update")
    ApiResponse<BusinessPolicyDTO> update(@RequestBody @Valid BusinessPolicyUpdateRequest request) {
        var response = policyService.update(request);
        return ApiResponse.<BusinessPolicyDTO>builder()
                .data(response)
                .build();
    }

    /**
     * Delete Policy
     */
    @DeleteMapping("/delete/{policyId}")
    ApiResponse<Object> delete(@PathVariable Long policyId) {
        var response = policyService.delete(policyId);
        return ApiResponse.builder()
                .data(response)
                .build();
    }

    /**
     * Get ALl
     */
    @GetMapping("")
    ApiResponse<SimplePage<BusinessPolicyDTO>> getAll(@PageableDefault Pageable pageable) {
        SimplePage<BusinessPolicyDTO> response = policyService.getAll(pageable);
        return ApiResponse.<SimplePage<BusinessPolicyDTO>>builder()
                .data(response)
                .build();
    }

}
