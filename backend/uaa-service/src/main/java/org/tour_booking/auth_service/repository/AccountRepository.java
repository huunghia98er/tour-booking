package org.tour_booking.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tour_booking.auth_service.model.entity.AccountEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query("SELECT a FROM AccountEntity a " +
            "WHERE a.username IN (:usernames) " +
            "OR a.email IN (:emails)")
    List<AccountEntity> findByUsernamesOrEmails(@Param("usernames") Set<String> usernames,
                                                @Param("emails") Set<String> emails);

    Optional<AccountEntity> findByUsername(String username);

}
