package org.tour_booking.auth_service.controller;

import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tour_booking.auth_service.model.request.AuthenticationRequest;
import org.tour_booking.auth_service.model.request.LogoutRequest;
import org.tour_booking.auth_service.model.request.RefreshRequest;
import org.tour_booking.auth_service.model.request.ValidTokenRequest;
import org.tour_booking.auth_service.model.response.ApiResponse;
import org.tour_booking.auth_service.model.response.AuthenticationResponse;
import org.tour_booking.auth_service.model.response.ValidTokenResponse;
import org.tour_booking.auth_service.service.AuthenticationService;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder().data(result).build();
    }

    @PostMapping("/valid")
    ApiResponse<ValidTokenResponse> validToken(@RequestBody ValidTokenRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.validToken(request);
        return ApiResponse.<ValidTokenResponse>builder().data(result).build();
    }

    @PostMapping("/refresh")
    ApiResponse<AuthenticationResponse> refresh(@RequestBody RefreshRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder().data(result).build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder().build();
    }

}
