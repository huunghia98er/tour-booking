package org.tour_booking.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tour_booking.auth_service.model.entity.PermissionEntity;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

    Optional<PermissionEntity> findByName(@Param("name") String name);

    @Query("SELECT p FROM PermissionEntity p " +
            "WHERE p.name IN (:names)")
    Set<PermissionEntity> findAllByNames(@Param("names") Set<String> names);

    void deleteByName(@Param("name") String name);

}
