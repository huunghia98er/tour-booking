package org.tour_booking.booking_service.controller;

import api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tour_booking.booking_service.models.request.BookingRequest;
import org.tour_booking.booking_service.service.BookingService;

import java.util.Objects;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/08/24
 */

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping("/create")
    public ApiResponse<String> createBooking(@RequestBody BookingRequest request) {
        String bookingCode = bookingService.createBooking(request);
        return Objects.isNull(bookingCode) ?
                ApiResponse.<String>builder()
                        .code(400)
                        .message("Create booking failed")
                        .build()
                :
                ApiResponse.<String>builder()
                        .code(201)
                        .message("Create booking successfully")
                        .data(bookingCode)
                        .build();
    }

    @PatchMapping("/{bookingCode}/cancel")
    public ApiResponse<String> cancelBooking(@PathVariable String bookingCode) {
        boolean isUpdated = bookingService.cancelBooking(bookingCode);

        return isUpdated ?
                ApiResponse.<String>builder()
                        .code(200)
                        .message("Cancel booking successfully")
                        .build()
                :
                ApiResponse.<String>builder()
                        .code(400)
                        .message("Cancel booking failed")
                        .build();
    }

}
