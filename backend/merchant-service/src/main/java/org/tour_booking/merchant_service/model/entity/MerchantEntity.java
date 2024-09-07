package org.tour_booking.merchant_service.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import model.BaseEntity;
import org.tour_booking.merchant_service.constant.VERIFICATION_STATUS;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Basic information about the agency
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
@Table(name = "merchant")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MerchantEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "contact_email", length = 100)
    String contactEmail;

    @Column(name = "contact_phone", length = 50)
    String contactPhone;

    @Column(name = "address")
    String address;

    @Column(name = "bank_account_number", length = 50)
    String bankAccountNumber;

    @Column(name = "bank_name")
    String bankName;

    @Column(name = "bank_account_holder_name")
    String bankAccountHolderName;

    @Builder.Default
    @Column(name = "registration_date")
    LocalDateTime registrationDate = LocalDateTime.now();

    @Builder.Default
    @Column(name = "verification_status")
    String verificationStatus = VERIFICATION_STATUS.PENDING.val;

    @Column(name = "approval_date")
    LocalDateTime approvalDate;

    @Builder.Default
    @Column(name = "is_active")
    Boolean isActive = false;

    @Column(name = "rejection_reason")
    String rejectionReason;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<AccountEntity> accounts;

}
