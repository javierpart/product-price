package com.inditextest.product_price.application.service;

import java.time.LocalDateTime;

import com.inditextest.product_price.application.port.GetActualPriceUseCase;
import com.inditextest.product_price.domain.port.PriceRepositoryPort;
import org.springframework.stereotype.Service;

import com.inditextest.product_price.application.exception.PriceNotFoundException;

import com.inditextest.product_price.domain.model.Price;

@Service
public class PriceService implements GetActualPriceUseCase {

    private final PriceRepositoryPort priceRepositoryPort;

    public PriceService(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    @Override
    public Price execute(Long productId, Long brandId, LocalDateTime date) {
        return priceRepositoryPort.findPriceByProductIdAndBrandIdAndDate(productId, brandId, date)
            .orElseThrow(() -> new PriceNotFoundException(productId, brandId, date));
    }
    
}
