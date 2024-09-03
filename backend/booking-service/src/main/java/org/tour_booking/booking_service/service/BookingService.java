package org.tour_booking.booking_service.service;

import org.springframework.stereotype.Service;
import org.tour_booking.booking_service.models.entity.Booking;
import org.tour_booking.booking_service.models.request.BookingRequest;

import java.util.List;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/08/24
 */

@Service
public interface BookingService {
    String createBooking(BookingRequest request);

    String updateBooking();

    String deleteBooking();

    Booking getBooking();

    List<Booking> getAllBooking();
}
