package org.tour_booking.payment_service.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import model.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @Author: luunguyen301297
 * @LastModified: 8/19/2024
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "commision_payment")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommissionPaymentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "paymentCode")
    String paymentCode;

    @Column(name = "merchant_id")
    Long merchantId;

    @Column(name = "tour_id")
    Long tourId;

    @Column(name = "booking_id")
    Long bookingId;

    @Column(name = "amount")
    BigDecimal amount;

    @Column(name = "currency", length = 50)
    String currency;

    @Column(name = "payment_status")
    String paymentStatus;

    @Column(name = "payment_Date")
    LocalDateTime paymentDate;

    @Column(name = "payment_link", columnDefinition = "TEXT")
    String paymentLink;

}
