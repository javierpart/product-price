package com.inditextest.product_price.application.exception;

import java.time.LocalDateTime;

public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException(Long productId, Long brandId, LocalDateTime date) {
        super(
            String.format(
                "Not found price for product: %d and brand: %d and date: %s",
                productId, brandId, date));
    }
}
