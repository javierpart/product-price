package com.inditextest.product_price.infrastructure.database.adapter;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.inditextest.product_price.infrastructure.database.entity.PriceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inditextest.product_price.domain.model.Price;
import com.inditextest.product_price.infrastructure.database.repository.PriceSpringDataRepository;
import com.inditextest.product_price.infrastructure.mapper.PriceEntityMapper;

@ExtendWith(MockitoExtension.class)
class PriceJPARepositoryAdapterTest {

    @Mock
    private PriceSpringDataRepository repository;

    @Mock
    private PriceEntityMapper mapper;

    private PriceJPARepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new PriceJPARepositoryAdapter(repository, mapper);
    }

    @Test
    void findPriceByProductIdAndBrandIdAndDate_WhenPriceExists_ShouldReturnPrice() {
        // Arrange
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.parse("2020-06-14T00:00:00");
        
        PriceEntity priceEntity = new PriceEntity();
        Price expectedPrice = new Price(1L, 35455L, 1, 35.50, 0, "EUR", 
            "2020-06-14-00.00.00", "2020-12-31-23.59.59");

        when(repository.findTopByDateBrandAndProduct(date, brandId, productId))
            .thenReturn(Optional.of(priceEntity));
        when(mapper.toPrice(priceEntity)).thenReturn(expectedPrice);

        // Act
        Optional<Price> result = adapter.findPriceByProductIdAndBrandIdAndDate(productId, brandId, date);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedPrice, result.get());
        verify(repository).findTopByDateBrandAndProduct(date, brandId, productId);
        verify(mapper).toPrice(priceEntity);
    }

    @Test
    void findPriceByProductIdAndBrandIdAndDate_WhenPriceDoesNotExist_ShouldReturnEmpty() {
        // Arrange
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.parse("2020-06-14T00:00:00");

        when(repository.findTopByDateBrandAndProduct(date, brandId, productId))
            .thenReturn(Optional.empty());

        // Act
        Optional<Price> result = adapter.findPriceByProductIdAndBrandIdAndDate(productId, brandId, date);

        // Assert
        assertTrue(result.isEmpty());
        verify(repository).findTopByDateBrandAndProduct(date, brandId, productId);
        verify(mapper, never()).toPrice(any());
    }


}