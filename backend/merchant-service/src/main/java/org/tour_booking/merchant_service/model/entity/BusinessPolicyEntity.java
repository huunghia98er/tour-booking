package org.tour_booking.merchant_service.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import model.BaseEntity;

import java.math.BigDecimal;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/9/2024
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "policy")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusinessPolicyEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    /**
     * Phần trăm hoa hồng
     */
    @Column(name = "commission_rate")
    BigDecimal commissionRate;

    /**
     * Thời hạn thanh toán (bao nhiêu ngày kể từ thời điểm tour hoàn thành)
     */
    @Column(name = "payment_terms")
    Integer paymentTerms;

    @Builder.Default
    @Column(name = "is_active")
    Boolean isActive = true;

}