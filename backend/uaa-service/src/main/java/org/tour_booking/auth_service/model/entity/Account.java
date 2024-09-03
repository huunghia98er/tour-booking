package org.tour_booking.auth_service.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import model.BaseEntity;

import java.util.Set;

/**
 * @Author: luunguyen301297
 * @LastModified: 8/19/2024
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "account")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "email")
    String email;

    @Column(name = "phone")
    String phone;

    @Builder.Default
    @Column(name = "is_active")
    boolean isActive = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "account_permissions",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "permissions_id")
    )
    Set<Permission> permissions;

}