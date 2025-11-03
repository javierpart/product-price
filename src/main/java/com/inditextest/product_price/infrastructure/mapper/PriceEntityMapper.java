package com.inditextest.product_price.infrastructure.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.inditextest.product_price.domain.model.Price;
import com.inditextest.product_price.infrastructure.database.entity.PriceEntity;
@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

    @Mapping(target = "brandId", source = "brand.id")
    @Mapping(target = "productId", source = "product.id")
    Price toPrice(PriceEntity priceEntity);

    @Named("localDateTimeToString")
    default String localDateTimeToString(LocalDateTime date) {
        if (date == null) {
            return null;
        }
        return date.format(DATE_FORMAT);
    }
}
