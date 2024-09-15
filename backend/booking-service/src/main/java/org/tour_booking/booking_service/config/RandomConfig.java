package org.tour_booking.booking_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/09/15
 */

@Configuration
public class RandomConfig {
    @Bean
    public Random random() {
        return new Random();
    }
}
