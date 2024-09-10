package org.tour_booking.auth_service.model.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.tour_booking.auth_service.validator.EmailConstrain;
import org.tour_booking.auth_service.validator.PhoneNumberConstrain;

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

        @Size(max = 255)
        String username;

        @Size(max = 50)
        String password;

        @EmailConstrain(message = "INVALID_EMAIL")
        @Size(max = 255)
        String email;

        @PhoneNumberConstrain(message = "INVALID_PHONE_NUMBER")
        String phone;

        Set<String> permissions;

    }

}
