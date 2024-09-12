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
@Table(name = "customer_payment")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RefundPaymentEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "paymentCode")
    String paymentCode;

    @Column(name = "customer_id")
    Long customerId;

    @Column(name = "booking_id")
    Long bookingId;

    @Column(name = "amount")
    BigDecimal amount;

    @Column(name = "currency", length = 50)
    String currency;

    @Column(name = "refundReason")
    String refundReason;

    @Column(name = "payment_status")
    String paymentStatus;

    @Column(name = "payment_method")
    String paymentMethod;

    @Column(name = "payment_Date")
    LocalDateTime paymentDate;

    @Column(name = "payment_link", columnDefinition = "TEXT")
    String paymentLink;

}
