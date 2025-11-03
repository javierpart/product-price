package com.inditextest.product_price.domain.model;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    @Test
    void defaultValuesAreNull() {
        Price price = new Price();
        assertNull(price.getBrandId(), "Expected default brandId to be null");
        assertNull(price.getProductId(), "Expected default productId to be null");
        assertNull(price.getTariffId(), "Expected default tariffId to be null");
        assertNull(price.getPrice(), "Expected default price to be null");
        assertNull(price.getPriority(), "Expected default priority to be null");
        assertNull(price.getCurrency(), "Expected default currency to be null");
        assertNull(price.getStartDate(), "Expected default startDate to be null");
        assertNull(price.getEndDate(), "Expected default endDate to be null");
    }

    @Test
    void gettersReturnSetValuesUsingReflection() throws Exception {
        Price price = new Price();

        Field brandIdField = Price.class.getDeclaredField("brandId");
        brandIdField.setAccessible(true);
        brandIdField.set(price, 100L);

        Field productIdField = Price.class.getDeclaredField("productId");
        productIdField.setAccessible(true);
        productIdField.set(price, 200L);

        Field tariffIdField = Price.class.getDeclaredField("tariffId");
        tariffIdField.setAccessible(true);
        tariffIdField.set(price, 1);

        Field priceField = Price.class.getDeclaredField("price");
        priceField.setAccessible(true);
        priceField.set(price, 19.99);

        Field priorityField = Price.class.getDeclaredField("priority");
        priorityField.setAccessible(true);
        priorityField.set(price, 0);

        Field currencyField = Price.class.getDeclaredField("currency");
        currencyField.setAccessible(true);
        currencyField.set(price, "EUR");

        Field startDateField = Price.class.getDeclaredField("startDate");
        startDateField.setAccessible(true);
        startDateField.set(price, "2020-06-14T00:00");

        Field endDateField = Price.class.getDeclaredField("endDate");
        endDateField.setAccessible(true);
        endDateField.set(price, "2020-12-31T23:59");

        assertEquals(100L, price.getBrandId());
        assertEquals(200L, price.getProductId());
        assertEquals(1, price.getTariffId());
        assertEquals(Double.valueOf(19.99), price.getPrice());
        assertEquals(0, price.getPriority());
        assertEquals("EUR", price.getCurrency());
        assertEquals("2020-06-14T00:00", price.getStartDate());
        assertEquals("2020-12-31T23:59", price.getEndDate());
    }
}