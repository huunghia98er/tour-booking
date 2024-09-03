package org.tour_booking.merchant_service.service;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/1/2024
 */
public interface EmailService {

    void sendEmail(String to, String subject, String body);

}