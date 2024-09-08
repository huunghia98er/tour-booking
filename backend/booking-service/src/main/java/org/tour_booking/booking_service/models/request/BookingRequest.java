package org.tour_booking.booking_service.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/08/24
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    private long tourId;
    private long customerId;
    private LocalDate departureDate;
    private double totalPrice;
    private String createdBy;
    private List<ParticipantsRequest> participants;
}
