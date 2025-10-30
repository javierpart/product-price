package com.inditextest.product_price.application.port;

import java.time.LocalDateTime;

import com.inditextest.product_price.domain.model.Price;

public interface  GetActualPriceUseCase {
    Price execute (Long productId, Long brandId, LocalDateTime date);
}

