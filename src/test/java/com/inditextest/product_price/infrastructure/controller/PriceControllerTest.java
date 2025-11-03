package com.inditextest.product_price.infrastructure.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.inditextest.product_price.application.port.GetActualPriceUseCase;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.inditextest.product_price.application.exception.PriceNotFoundException;
import com.inditextest.product_price.domain.model.Price;
import com.inditextest.product_price.infrastructure.controller.dto.PriceResponse;
import com.inditextest.product_price.infrastructure.mapper.PriceResponseMapper;

@WebMvcTest(PriceController.class)
class PriceControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetActualPriceUseCase getActualPriceUseCase;

    @MockBean
    private PriceResponseMapper priceResponseMapper;


    @Test
    void getActualPrice_WhenPriceExists_ShouldReturnPrice() throws Exception {
        // Arrange
        String date = "2020-06-14-00.00.00";
        Long productId = 35455L;
        Long brandId = 1L;

        Price price = new Price(brandId, productId, 1, 35.50, 0, "EUR", date, "2020-12-31-23.59.59");
        PriceResponse response = new PriceResponse(brandId, productId, 1,"2020-12-31-23.59.59", "2020-12-31-23.59.59", 35.50);

        when(getActualPriceUseCase.execute(productId, brandId, LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"))))
            .thenReturn(price);
        when(priceResponseMapper.toDto(price)).thenReturn(response);

        // Act & Assert
        mockMvc.perform(get("/prices/date/{date}/productid/{productid}/brandid/{brandid}", date, productId, brandId)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.brandId").value(brandId))
            .andExpect(jsonPath("$.productId").value(productId))
            .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    void getActualPrice_WhenPriceNotFound_ShouldReturnNotFound() throws Exception {
        // Arrange
        String date = "2020-06-14-00.00.00";
        Long productId = 35455L;
        Long brandId = 1L;

        when(getActualPriceUseCase.execute(any(), any(), any()))
            .thenThrow(new PriceNotFoundException(productId, brandId, LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"))));

        // Act & Assert
        mockMvc.perform(get("/prices/date/{date}/productid/{productid}/brandid/{brandid}", date, productId, brandId)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    void getActualPrice_WhenInvalidDateFormat_ShouldReturnBadRequest() throws Exception {
        // Arrange
        String invalidDate = "202-06-14";
        Long productId = 35455L;
        Long brandId = 1L;

        // Act & Assert
        mockMvc.perform(get("/prices/date/{date}/productid/{productid}/brandid/{brandid}", invalidDate, productId, brandId)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }
}