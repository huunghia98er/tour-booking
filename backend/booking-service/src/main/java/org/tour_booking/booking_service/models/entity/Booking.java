package org.tour_booking.booking_service.models.entity;

import jakarta.persistence.*;
import lombok.*;
import model.BaseEntity;
import org.tour_booking.booking_service.constant.BookingStatus;

import java.time.LocalDate;
import java.util.List;

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
@Table(name = "bookings")
public class Booking extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookingCode;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private BookingStatus bookingStatus = BookingStatus.PENDING;

    private long tourId;

    private long customerId;

    private Long paymentId;

    @Builder.Default
    private boolean isPaid = false;

    @Builder.Default
    private boolean isDeleted = false;

    private LocalDate departureDate;

    private double totalPrice;

    @OneToMany(mappedBy = "booking", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Participants> participants;

    private String createdBy;
}
