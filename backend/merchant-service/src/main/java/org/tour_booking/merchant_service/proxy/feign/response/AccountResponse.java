package org.tour_booking.merchant_service.proxy.feign.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponse {

    Long id;
    String username;
    String email;
    String phone;
    boolean isActive;
    Set<PermissionResponse> permissions;

}
