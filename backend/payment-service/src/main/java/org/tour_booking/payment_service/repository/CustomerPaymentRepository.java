package org.tour_booking.payment_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tour_booking.payment_service.model.entity.CustomerPaymentEntity;

import java.time.LocalDateTime;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/11/2024
 */
@Repository
public interface CustomerPaymentRepository extends JpaRepository<CustomerPaymentEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE CustomerPaymentEntity p SET p.paymentStatus = :state, p.updatedAt = :updatedAt WHERE p.paymentCode = :paymentCode")
    void updatePaymentCodeById(@Param("paymentCode") String paymentCode,
                               @Param("state") String state,
                               @Param("updatedAt") LocalDateTime updatedAt);

    @Modifying
    @Transactional
    @Query("UPDATE CustomerPaymentEntity p SET p.paymentStatus = :state, p.updatedAt = :updatedAt WHERE p.paymentCode = :paymentCode")
    void updatePaymentCodeById(@Param("paymentCode") String paymentCode,
                               @Param("state") String state,
                               @Param("detail") String detail,
                               @Param("updatedAt") LocalDateTime updatedAt);

    @Modifying
    @Transactional
    @Query("UPDATE CustomerPaymentEntity p SET p.paymentLink = :paymentLink, p.paymentStatus = :paymentStatus, p.updatedAt = :updatedAt WHERE p.paymentCode = :paymentCode")
    void updatePaymentLink(@Param("paymentCode") String paymentCode,
                           @Param("status") String paymentStatus,
                           @Param("paymentLink") String paymentLink,
                           @Param("updatedAt") LocalDateTime updatedAt);

    @Query("SELECT p.paymentLink FROM CustomerPaymentEntity p WHERE p.paymentCode = :paymentId")
    String findPaymentLinkByPaymentId(@Param("paymentId") String paymentId);

}
