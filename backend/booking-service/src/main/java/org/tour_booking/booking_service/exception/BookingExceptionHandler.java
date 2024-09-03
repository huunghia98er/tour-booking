package org.tour_booking.booking_service.exception;

import exception.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/08/25
 */

@ControllerAdvice
@Slf4j(topic = "BOOKING-EXCEPTION-HANDLER")
public class BookingExceptionHandler extends GlobalExceptionHandler {
}
