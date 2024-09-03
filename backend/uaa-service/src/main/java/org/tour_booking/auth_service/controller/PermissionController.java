package org.tour_booking.auth_service.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.tour_booking.auth_service.model.request.PermissionRequest;
import org.tour_booking.auth_service.model.response.ApiResponse;
import org.tour_booking.auth_service.model.response.PermissionResponse;
import org.tour_booking.auth_service.service.PermissionService;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {

    PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {
        return ApiResponse.<PermissionResponse>builder()
                .data(permissionService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .data(permissionService.getAll())
                .build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<?> delete(@PathVariable String permission) {
        permissionService.delete(permission);
        return ApiResponse.<String>builder()
                .data("success deleted")
                .build();
    }

}
