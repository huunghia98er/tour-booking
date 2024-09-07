package org.tour_booking.auth_service.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import exception.AppException;
import exception.ERROR_CODE;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.tour_booking.auth_service.model.entity.AccountEntity;
import org.tour_booking.auth_service.model.entity.VerificationTokenEntity;
import org.tour_booking.auth_service.repository.InvalidatedTokenRepository;
import org.tour_booking.auth_service.repository.VerificationTokenRepository;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.UUID;

/**
 * @Author: luunguyen301297
 * @LastModified: 8/25/2024
 */
@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TokenService {

    @NonFinal
    @Value("${jwt.signerKey}")
    String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    long REFRESHABLE_DURATION;

    static String TOKEN_PREFIX = "Bearer ";
    static String PAYLOAD_USER_ID = "userId";
    static String AUTHOR_HEADER_KEY = "Authorization";
    static String AUTH_SCOPE = "scope";
    static String PERMISSION_PREFIX = "PERMISSION_";

    InvalidatedTokenRepository invalidTokenRepo;
    VerificationTokenRepository verificationTokenRepo;

    public VerificationTokenEntity createVerificationToken(AccountEntity account) {
        VerificationTokenEntity token = VerificationTokenEntity.builder()
                .token(UUID.randomUUID().toString())
                .account(account)
                .expiryDate(LocalDateTime.now().plusDays(1))
                .build();
        return verificationTokenRepo.save(token);
    }

    public Optional<VerificationTokenEntity> getToken(String token) {
        return verificationTokenRepo.findByToken(token);
    }


    public String generateToken(AccountEntity account, boolean isAccessToken) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        long expiryTime = isAccessToken ? VALID_DURATION : REFRESHABLE_DURATION;
        Date issueTime = new Date();
        Date expiryDate = new Date(Instant.ofEpochMilli(issueTime.getTime())
                .plus(expiryTime, ChronoUnit.SECONDS)
                .toEpochMilli());

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(account.getUsername())
                .issueTime(issueTime)
                .expirationTime(expiryDate)
                .jwtID(UUID.randomUUID().toString())
                .claim(AUTH_SCOPE, this.buildScope(account))
                .claim(PAYLOAD_USER_ID, account.getId())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new AppException(ERROR_CODE.UNAUTHENTICATED);
        }
    }

    public SignedJWT verifyToken(String token, boolean isRefreshToken) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        boolean verified = signedJWT.verify(verifier);
        long expiryTime = isRefreshToken ? REFRESHABLE_DURATION : VALID_DURATION;
        Date expiryDate = isRefreshToken ?
                new Date(signedJWT.getJWTClaimsSet().getIssueTime().toInstant().plus(expiryTime, ChronoUnit.SECONDS).toEpochMilli())
                : signedJWT.getJWTClaimsSet().getExpirationTime();

        if (!verified) {
            throw new AppException(ERROR_CODE.UNAUTHENTICATED);
        }

        boolean isInvalidated = invalidTokenRepo.existsById(signedJWT.getJWTClaimsSet().getJWTID());
        if (expiryDate.before(new Date()) || isInvalidated) {
            throw new AppException(ERROR_CODE.INVALID_TOKEN);
        }

        return signedJWT;
    }

    private String buildScope(AccountEntity account) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (!CollectionUtils.isEmpty(account.getPermissions())) {
            account.getPermissions().forEach(permission
                    -> stringJoiner.add(PERMISSION_PREFIX + permission.getName()));
        }
        return stringJoiner.toString();
    }

    public String extractToken(HttpServletRequest request) {
        String token = request.getHeader(AUTHOR_HEADER_KEY);
        if (token != null && token.startsWith(TOKEN_PREFIX)) {
            return token.substring(TOKEN_PREFIX.length());
        }
        throw new AppException(ERROR_CODE.INVALID_TOKEN);
    }

    public Long extractAccountIdFromToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
            return claims.getLongClaim(PAYLOAD_USER_ID);
        } catch (ParseException e) {
            throw new AppException(ERROR_CODE.INVALID_TOKEN);
        }
    }

}
