package org.tour_booking.merchant_service.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import model.BaseEntity;

import java.time.LocalDateTime;

/**
 * @Author: luunguyen301297
 * @LastModified: 8/31/2024
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "business_license")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusinessLicenseEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "merchant_id")
    Long merchantId;

    @Column(name = "business_license_image_url")
    String businessLicenseImageUrl;

    @Column(name = "license_expiry_date")
    LocalDateTime licenseExpiryDate;

}
