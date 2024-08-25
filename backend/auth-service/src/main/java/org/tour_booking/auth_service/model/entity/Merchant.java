package org.tour_booking.auth_service.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import model.BaseEntity;
import org.tour_booking.auth_service.constant.VERIFICATION_STATUS;

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
@Entity(name = "merchant")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Merchant extends BaseEntity {

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

    @Column(name = "registration_date")
    LocalDateTime registrationDate;

    @Builder.Default
    @Column(name = "verification_status")
    @Enumerated(EnumType.STRING)
    VERIFICATION_STATUS verificationStatus = VERIFICATION_STATUS.PENDING;

    @Column(name = "approval_date")
    LocalDateTime approvalDate;

    @Builder.Default
    @Column(name = "is_active")
    boolean isActive = false;

    @Column(name = "rejection_reason")
    String rejectionReason;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "account_merchant",
            joinColumns = @JoinColumn(name = "merchant_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id")
    )
    Set<Account> accounts;

}
