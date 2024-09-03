package org.tour_booking.merchant_service.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.tour_booking.merchant_service.constant.PERMISSION;

import java.util.List;
import java.util.Set;

/**
 * @Author: luunguyen301297
 * @LastModified: 8/31/2024
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateMerchantActorRequest {

    Long merchantId;

    List<Account> accounts;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Account {

        String username;

        String password;

        String email;

        String phone;

        Set<PERMISSION> permissions;

    }

}
