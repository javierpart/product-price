package com.inditextest.product_price.infrastructure.database.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import com.inditextest.product_price.infrastructure.database.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PriceSpringDataRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p " + 
           "WHERE p.startDate <= :date " + 
           "  AND p.endDate >= :date " + 
           "  AND p.brand.id = :brandId " +
           "  AND p.product.id = :productId " +
           "  ORDER BY p.priority DESC " +
           "  LIMIT 1")
    Optional<PriceEntity> findTopByDateBrandAndProduct(
        @Param("date") LocalDateTime date,
        @Param("brandId") Long brandId, 
        @Param("productId") Long productId 
    );
    
}
