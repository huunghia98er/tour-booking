package org.tour_booking.booking_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tour_booking.booking_service.models.entity.Participants;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/08/24
 */

@Repository
public interface ParticipantsRepository extends JpaRepository<Participants, Long> {
}
