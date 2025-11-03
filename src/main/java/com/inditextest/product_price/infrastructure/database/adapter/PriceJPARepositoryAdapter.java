package com.inditextest.product_price.infrastructure.database.adapter;

import java.time.LocalDateTime;
import java.util.Optional;

import com.inditextest.product_price.domain.port.PriceRepositoryPort;
import org.springframework.stereotype.Repository;

import com.inditextest.product_price.domain.model.Price;
import com.inditextest.product_price.infrastructure.database.repository.PriceSpringDataRepository;
import com.inditextest.product_price.infrastructure.mapper.PriceEntityMapper;

@Repository
public class PriceJPARepositoryAdapter implements PriceRepositoryPort {
    
    private final PriceSpringDataRepository priceSpringDataRepository;
    private final PriceEntityMapper priceEntityMapper;

    public PriceJPARepositoryAdapter(PriceSpringDataRepository priceSpringDataRepository,
            PriceEntityMapper priceEntityMapper) {
        this.priceSpringDataRepository = priceSpringDataRepository;
        this.priceEntityMapper = priceEntityMapper;
    }   

    @Override
    public Optional<Price> findPriceByProductIdAndBrandIdAndDate(Long productId, Long brandId, LocalDateTime date) {
        return priceSpringDataRepository
            .findTopByDateBrandAndProduct(date, brandId, productId)
            .map(priceEntityMapper::toPrice);
    }
    
}
