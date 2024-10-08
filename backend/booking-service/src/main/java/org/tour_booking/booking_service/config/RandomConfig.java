package org.tour_booking.booking_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/09/15
 */

@Configuration
public class RandomConfig {
    @Bean
    public SecureRandom random() {
        return new SecureRandom();
    }
}
