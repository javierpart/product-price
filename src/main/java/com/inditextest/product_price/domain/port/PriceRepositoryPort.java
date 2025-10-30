package com.inditextest.product_price.domain.port;

import java.util.Optional;

import com.inditextest.product_price.domain.model.Price;

public interface PriceRepositoryPort {

    Optional<Price> findPriceByProductIdAndBrandIdAndDate(Long productId, Long brandId, java.time.LocalDateTime date);
}
