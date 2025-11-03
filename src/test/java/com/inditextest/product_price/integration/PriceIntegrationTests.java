package com.inditextest.product_price.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.SneakyThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class PriceIntegrationTests {

    @Autowired
    MockMvc mockMvc;

    @ParameterizedTest
    @ValueSource(strings = {
        "/prices/date/2020-06-14-10.00.00/productid/35455/brandid/1",
        "/prices/date/2020-06-14-16.00.00/productid/35455/brandid/1",
        "/prices/date/2020-06-15-21.00.00/productid/35455/brandid/1",
        "/prices/date/2020-06-15-10.00.00/productid/35455/brandid/1",
        "/prices/date/2020-06-16-21.00.00/productid/35455/brandid/1"
    })
    @SneakyThrows
    void testPrice(String url) {
        mockMvc.perform(MockMvcRequestBuilders.get(url))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(
                result -> result.getResponse().getContentAsString().equalsIgnoreCase("38.95"));
    }

    @Test
    @SneakyThrows
    void testPriceNotFound() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/prices/date/2020-06-15-20.00.00/productid/35455/brandid/7"))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(result -> result.getResponse().getContentAsString().contains(
                "Not found price for product: 35455 and brand: 7 and date: 2020-06-15T20:00"));
    }
}
