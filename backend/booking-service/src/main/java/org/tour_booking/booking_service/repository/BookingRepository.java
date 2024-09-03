package org.tour_booking.booking_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tour_booking.booking_service.models.entity.Booking;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/08/24
 */

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
