package org.tour_booking.booking_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.tour_booking.booking_service.models.entity.Booking;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/08/25
 */

@Service
@RequiredArgsConstructor
public class KafkaService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(Booking booking) {
        kafkaTemplate.send("booking", booking);
    }
}
