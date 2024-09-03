package org.tour_booking.booking_service.models.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.CascadeType.ALL;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/08/24
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "participants")
public class Participants {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, cascade = ALL)
    private Booking booking;
    private int age;
    private int quantity;
}
