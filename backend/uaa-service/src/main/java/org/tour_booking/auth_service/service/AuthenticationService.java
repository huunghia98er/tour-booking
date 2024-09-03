package org.tour_booking.auth_service.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import exception.AppException;
import exception.ERROR_CODE;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tour_booking.auth_service.model.entity.Account;
import org.tour_booking.auth_service.model.entity.InvalidatedToken;
import org.tour_booking.auth_service.model.request.AuthenticationRequest;
import org.tour_booking.auth_service.model.request.LogoutRequest;
import org.tour_booking.auth_service.model.request.RefreshRequest;
import org.tour_booking.auth_service.model.request.ValidTokenRequest;
import org.tour_booking.auth_service.model.response.AuthenticationResponse;
import org.tour_booking.auth_service.model.response.ValidTokenResponse;
import org.tour_booking.auth_service.repository.AccountRepository;
import org.tour_booking.auth_service.repository.InvalidatedTokenRepository;

import java.text.ParseException;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    AccountRepository accountRepo;
    InvalidatedTokenRepository invalidTokenRepo;
    TokenService tokenService;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        Account account = accountRepo
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ERROR_CODE.USER_NOT_EXISTED));

        boolean authenticated = passwordEncoder.matches(request.getPassword(), account.getPassword());
        if (!authenticated) throw new AppException(ERROR_CODE.UNAUTHENTICATED);

        String accessToken = tokenService.generateToken(account, true);
        String refreshToken = tokenService.generateToken(account, false);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public ValidTokenResponse validToken(ValidTokenRequest request) throws JOSEException, ParseException {
        boolean isValid = true;
        try {
            tokenService.verifyToken(request.getToken(), false);
        } catch (AppException e) {
            isValid = false;
        }
        return ValidTokenResponse.builder().valid(isValid).build();
    }

    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            SignedJWT signedJWT = tokenService.verifyToken(request.getToken(), true);
            String jit = signedJWT.getJWTClaimsSet().getJWTID();
            Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                    .id(jit)
                    .expiryTime(expiryTime)
                    .build();
            invalidTokenRepo.save(invalidatedToken);
        } catch (AppException e) {
            throw new AppException(ERROR_CODE.INVALID_TOKEN);
        }
    }

    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        SignedJWT signedJWT = tokenService.verifyToken(request.getToken(), true);
        String jit = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryTime(expiryTime)
                .build();
        invalidTokenRepo.save(invalidatedToken);

        String username = signedJWT.getJWTClaimsSet().getSubject();
        Account account = accountRepo.findByUsername(username)
                .orElseThrow(() -> new AppException(ERROR_CODE.UNAUTHENTICATED));

        String accessToken = tokenService.generateToken(account, true);
        String refreshToken = tokenService.generateToken(account, false);

        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
