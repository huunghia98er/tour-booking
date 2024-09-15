package org.tour_booking.booking_service.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.tour_booking.booking_service.constant.BookingStatus;
import org.tour_booking.booking_service.mapper.BookingMapper;
import org.tour_booking.booking_service.models.entity.Booking;
import org.tour_booking.booking_service.models.request.BookingRequest;

import java.util.Random;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/08/24
 */

@Component
@RequiredArgsConstructor
public class BookingUtils {
    private static final String BOOKING_CODE_PREFIX = "STB";
    private final BookingMapper bookingMapper;
    private final Random random;

    public Booking convertRequestToEntity(BookingRequest request) {
        Booking booking = bookingMapper.fromRequestToEntity(request);

        booking.setBookingCode(this.generateBookingCode());
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setPaid(false);
        booking.setDeleted(false);
        booking.setCreatedBy(request.getCreatedBy());

        booking.getParticipants().forEach(oderItem -> {
            if (oderItem.getBooking() == null) {
                oderItem.setBooking(booking);
            }
        });

        return booking;
    }

    public String generateBookingCode() {
        return BOOKING_CODE_PREFIX + System.currentTimeMillis() + random.nextLong();
    }

}


