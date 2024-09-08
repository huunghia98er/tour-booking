package org.tour_booking.auth_service.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountCreationRequest {

    String username;
    String password;
    String email;
    String phone;
    Long merchantId;

}
