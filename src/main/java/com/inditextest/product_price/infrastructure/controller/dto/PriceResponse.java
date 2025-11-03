package com.inditextest.product_price.infrastructure.controller.dto;

public record PriceResponse(
    Long brandId,
    Long productId,
    Integer tariffId,
    String startDate,
    String endDate,
    Double price
) {
    
}
