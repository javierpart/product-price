package com.inditextest.product_price.domain.model;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class BrandTest {

    @Test
    void defaultValuesAreNull() {
        Brand brand = new Brand();
        assertNull(brand.getId(), "Expected default id to be null");
        assertNull(brand.getName(), "Expected default name to be null");
    }

    @Test
    void gettersReturnSetValuesUsingReflection() throws Exception {
        Brand brand = new Brand();

        Field idField = Brand.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(brand, 1L);

        Field nameField = Brand.class.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(brand, "ZARA");

        assertEquals(1L, brand.getId());
        assertEquals("ZARA", brand.getName());
    }
}
