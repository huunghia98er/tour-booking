package org.tour_booking.payment_service.kafka.event;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/11/2024
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event {

    String event;

}
