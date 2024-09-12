package org.tour_booking.payment_service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/11/2024
 */
@Slf4j
public class KafkaService {

    KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(String topic, Object event) {
        kafkaTemplate.send(topic, event);
    }

    /**
     * consume event from Booking Service
     */
    @KafkaListener(topics = "${kafka.consumer.topic.booking}")
    public void consume(Object event) {
        log.info("Received message: {}", event);
       //todo: call api payment,
    }

}
