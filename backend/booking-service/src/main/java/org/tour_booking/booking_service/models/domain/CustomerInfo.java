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
public class CustomerInfo {
    private long id;
    private String name;
    private String email;
    private String phone;
    private String address;
}
