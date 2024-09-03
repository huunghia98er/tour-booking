package org.tour_booking.booking_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.tour_booking.booking_service.models.entity.Booking;
import org.tour_booking.booking_service.models.request.BookingRequest;
import org.tour_booking.booking_service.repository.BookingRepository;
import org.tour_booking.booking_service.utils.BookingUtils;

import java.util.List;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/08/24
 */

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingUtils bookingUtils;
    private final KafkaService kafkaService;

    @Override
    public String createBooking(BookingRequest request) {
        Booking entityRequest = bookingUtils.convertRequestToEntity(request);

        Booking booking = bookingRepository.save(entityRequest);

        kafkaService.sendMessage(booking);

        return booking.getBookingCode();
    }

    @Override
    public String updateBooking() {
        return "";
    }

    @Override
    public String deleteBooking() {
        return "";
    }

    @Override
    public Booking getBooking() {
        return null;
    }

    @Override
    public List<Booking> getAllBooking() {
        return List.of();
    }
}
