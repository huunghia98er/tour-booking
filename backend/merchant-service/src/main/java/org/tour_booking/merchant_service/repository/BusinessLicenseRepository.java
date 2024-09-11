package org.tour_booking.merchant_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tour_booking.merchant_service.model.entity.BusinessLicenseEntity;

import java.util.List;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/1/2024
 */
@Repository
public interface BusinessLicenseRepository extends JpaRepository<BusinessLicenseEntity, Long> {

    @Query("SELECT b FROM BusinessLicenseEntity b " +
            "JOIN MerchantEntity m ON m.id = b.merchantId " +
            "WHERE m.id in (:merchantIds)")
    List<BusinessLicenseEntity> findByMerchantIds(@Param("merchantIds") List<Long> merchantIds);

}
