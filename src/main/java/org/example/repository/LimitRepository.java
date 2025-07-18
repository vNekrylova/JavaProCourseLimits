package org.example.repository;

import org.example.entity.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {

    Optional<Limit> findByClientId(Long clientId);

    @Modifying
    @Query(value = "update limits set daily_limit = :defaultValue", nativeQuery = true)
    void updateDailyLimit(@Param("defaultValue") BigDecimal defaultValue);
}
