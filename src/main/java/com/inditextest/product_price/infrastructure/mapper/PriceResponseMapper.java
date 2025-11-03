package com.inditextest.product_price.infrastructure.mapper;

import org.mapstruct.Mapper;

import com.inditextest.product_price.domain.model.Price;
import com.inditextest.product_price.infrastructure.controller.dto.PriceResponse;

@Mapper(componentModel = "spring")
public interface PriceResponseMapper {

    PriceResponse toDto(Price price);
}