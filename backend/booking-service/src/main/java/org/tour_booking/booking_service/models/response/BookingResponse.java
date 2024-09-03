package org.tour_booking.booking_service.models.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tour_booking.booking_service.constant.BookingStatus;
import org.tour_booking.booking_service.models.domain.CustomerInfo;
import org.tour_booking.booking_service.models.domain.Tour;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/08/24
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponse {
    private Long id;
    private String bookingCode;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    private Tour tour;
    private CustomerInfo customer;
    private long paymentId;
    private boolean isPaid;
    private boolean isDeleted;
    private LocalDateTime departureDate;
    private double totalPrice;
    private List<ParticipantsResponse> participants;
}
