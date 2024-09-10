package org.tour_booking.auth_service.model.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.tour_booking.auth_service.validator.EmailConstrain;
import org.tour_booking.auth_service.validator.PhoneNumberConstrain;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountCreationRequest {

    @Size(max = 255)
    String username;

    @Size(max = 50)
    String password;

    @EmailConstrain(message = "INVALID_EMAIL")
    @Size(max = 255)
    String email;

    @PhoneNumberConstrain(message = "INVALID_PHONE_NUMBER")
    String phone;

    Long merchantId;

}
