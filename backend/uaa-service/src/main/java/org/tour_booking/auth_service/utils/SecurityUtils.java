package org.tour_booking.auth_service.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;

/**
 * @Author: luunguyen301297
 * @LastModified: 8/31/2024
 */
public class SecurityUtils {

    /**
     * Lấy ra username của người dùng hiện tại từ SecurityContext.
     *
     * @return username
     */
    public static String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        }
        return null;
    }

    /**
     * Lấy ra ID của người dùng hiện tại từ token JWT.
     *
     * @return user ID
     */
    public static String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getSubject();
        }
        return null;
    }

    /**
     * Lấy ra token của người dùng hiện tại từ SecurityContext.
     *
     * @return Jwt token
     */
    public static String getCurrentUserToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getCredentials() instanceof String) {
            return (String) authentication.getCredentials();
        }
        return null;
    }

}
