package org.tour_booking.merchant_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tour_booking.merchant_service.model.entity.MerchantEntity;
import org.tour_booking.merchant_service.repository.filter.MerchantByFilter;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/1/2024
 */
@Repository
public interface MerchantRepository extends JpaRepository<MerchantEntity, Long> {

    @Query("SELECT m FROM MerchantEntity m " +
            "WHERE (:#{#filter.verificationStatus} IS NULL OR m.verificationStatus = :#{#filter.verificationStatus}) " +
            "AND (:#{#filter.isActive} IS NULL OR m.isActive = :#{#filter.isActive}) " +
            "AND (:#{#filter.fromDate} IS NULL AND :#{#filter.toDate} IS NULL " +
            "OR m.registrationDate BETWEEN :#{#filter.fromDate} AND :#{#filter.toDate})")
    Page<MerchantEntity> findAllByVerificationStatusAndIsActive(@Param("filter") MerchantByFilter filter,
                                                                Pageable pageable);

}
