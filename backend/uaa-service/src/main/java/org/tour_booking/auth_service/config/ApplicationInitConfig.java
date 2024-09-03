package org.tour_booking.auth_service.config;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.tour_booking.auth_service.constant.PERMISSION;
import org.tour_booking.auth_service.model.entity.Account;
import org.tour_booking.auth_service.model.entity.Permission;
import org.tour_booking.auth_service.repository.AccountRepository;
import org.tour_booking.auth_service.repository.PermissionRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncode;

    PermissionRepository permissionRepo;

    @Bean
    ApplicationRunner runner(AccountRepository accountRepo) {
        return args -> {
            if (accountRepo.findByUsername("admin").isEmpty()) {
                Account account = Account.builder()
                        .username("admin")
                        .password(passwordEncode.encode("admin@123"))
                        .permissions(new HashSet<>(permissionRepo.saveAll(this.createAdminPermissions())))
                        .build();

                accountRepo.save(account);
                log.warn("admin has been created with default password: admin@123");
            }
        };
    }

    private Set<Permission> createAdminPermissions() {
        Permission permissionApprove = Permission.builder()
                .name(PERMISSION.APPROVE_MERCHANT.val)
                .build();
        Permission permissionReject = Permission.builder()
                .name(PERMISSION.REJECT_MERCHANT.val)
                .build();
        return new HashSet<>(List.of(permissionApprove, permissionReject));
    }

}
