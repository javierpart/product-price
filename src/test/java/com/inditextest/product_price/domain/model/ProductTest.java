package com.inditextest.product_price.domain.model;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void defaultValuesAreNull() {
        Product product = new Product();
        assertNull(product.getId(), "Expected default id to be null");
        assertNull(product.getName(), "Expected default name to be null");
    }

    @Test
    void gettersReturnSetValuesUsingReflection() throws Exception {
        Product product = new Product();

        Field idField = Product.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(product, 1L);

        Field nameField = Product.class.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(product, "CAMISETA");

        assertEquals(1L, product.getId());
        assertEquals("CAMISETA", product.getName());
    }
}
