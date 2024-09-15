package org.tour_booking.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.tour_booking.auth_service.model.entity.VerificationTokenEntity;

import java.util.Optional;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/6/2024
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationTokenEntity, Long> {

    Optional<VerificationTokenEntity> findByToken(@Param("token") String token);

}
