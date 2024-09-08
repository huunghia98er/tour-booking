package org.tour_booking.booking_service.models.domain;

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
public class Tour {
    private long id;
    private String name;
    private String description;
    private double price;
    private int duration;
    private String destination;
    private String createdBy;
    private String updatedBy;
    private boolean isDeleted;
}
