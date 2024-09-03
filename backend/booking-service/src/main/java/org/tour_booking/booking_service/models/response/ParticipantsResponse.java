package org.tour_booking.booking_service.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/08/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipantsResponse {
    private long id;
    private int age;
    private int quantity;
}
