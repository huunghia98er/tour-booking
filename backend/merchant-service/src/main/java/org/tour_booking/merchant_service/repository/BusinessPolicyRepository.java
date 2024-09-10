package org.tour_booking.merchant_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tour_booking.merchant_service.model.entity.BusinessPolicyEntity;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/1/2024
 */
@Repository
public interface BusinessPolicyRepository extends JpaRepository<BusinessPolicyEntity, Long> {

    @Query("SELECT b FROM BusinessPolicyEntity b " +
            "WHERE b.isActive = TRUE")
    Page<BusinessPolicyEntity> findAllByIsActive(@Param("isActive") Boolean isActive,
                                                 Pageable pageable);

}
