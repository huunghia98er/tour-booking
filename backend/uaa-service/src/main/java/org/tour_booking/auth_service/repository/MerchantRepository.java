package org.tour_booking.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tour_booking.auth_service.model.entity.MerchantEntity;

@Repository
public interface MerchantRepository extends JpaRepository<MerchantEntity, Long> {

}
