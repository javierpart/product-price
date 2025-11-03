package com.inditextest.product_price.infrastructure.database.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductEntityTest {

    @Test
    void getId() {
        ProductEntity p = ProductEntity.builder().id(1L).name("Product A").build();
        assertEquals(1L, p.getId());
    }

    @Test
    void getName() {
        ProductEntity p = ProductEntity.builder().id(1L).name("Product A").build();
        assertEquals("Product A", p.getName());
    }

    @Test
    void setId() {
        ProductEntity p = new ProductEntity();
        p.setId(42L);
        assertEquals(42L, p.getId());
    }

    @Test
    void setName() {
        ProductEntity p = new ProductEntity();
        p.setName("New Name");
        assertEquals("New Name", p.getName());
    }

    @Test
    void testEquals() {
        ProductEntity a = ProductEntity.builder().id(1L).name("X").build();
        ProductEntity b = ProductEntity.builder().id(1L).name("X").build();
        ProductEntity c = ProductEntity.builder().id(2L).name("Y").build();

        assertEquals(a, b);
        assertNotEquals(a, c);
        assertNotEquals(a, null);
        assertNotEquals(a, new Object());
    }

    @Test
    void canEqual() {
        ProductEntity a = ProductEntity.builder().id(1L).name("X").build();
        ProductEntity b = ProductEntity.builder().id(1L).name("X").build();

        // canEqual is protected in Lombok-generated code but accessible in the same package
        assertTrue(a.canEqual(b));
        assertFalse(a.canEqual(new Object()));
    }

    @Test
    void testHashCode() {
        ProductEntity a = ProductEntity.builder().id(1L).name("X").build();
        ProductEntity b = ProductEntity.builder().id(1L).name("X").build();

        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    void testToString() {
        ProductEntity a = ProductEntity.builder().id(5L).name("Z").build();
        String s = a.toString();
        assertTrue(s.contains("ProductEntity"));
        assertTrue(s.contains("id=5"));
        assertTrue(s.contains("name=Z"));
    }

    @Test
    void builder() {
        ProductEntity p = ProductEntity.builder().id(3L).name("Built").build();
        assertEquals(3L, p.getId());
        assertEquals("Built", p.getName());
    }
}