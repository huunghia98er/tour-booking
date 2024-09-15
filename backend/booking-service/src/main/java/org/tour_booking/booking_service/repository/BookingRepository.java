package org.tour_booking.booking_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tour_booking.booking_service.models.entity.Booking;

import java.util.Optional;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/08/24
 */

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByBookingCode(@Param("bookingCode") String bookingCode);

    Boolean existsByBookingCode(@Param("bookingCode") String bookingCode);

    Boolean saveAndGetBoolean(Booking booking);
}
