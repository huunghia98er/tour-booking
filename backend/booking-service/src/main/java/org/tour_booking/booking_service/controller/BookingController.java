package org.tour_booking.booking_service.controller;

import api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tour_booking.booking_service.models.request.BookingRequest;
import org.tour_booking.booking_service.service.BookingService;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/08/24
 */

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/create-booking")
    public ApiResponse<String> createBooking(@RequestBody BookingRequest request) {
        return ApiResponse.<String>builder()
                .code(201)
                .message("Create booking successfully")
                .data(bookingService.createBooking(request))
                .build();
    }

}
