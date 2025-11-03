package com.inditextest.product_price.infrastructure.controller;

import java.time.LocalDateTime;

import com.inditextest.product_price.application.port.GetActualPriceUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inditextest.product_price.infrastructure.controller.dto.PriceResponse;
import com.inditextest.product_price.infrastructure.mapper.PriceResponseMapper;

@RestController
@RequestMapping("/prices")
public class PriceController {
    
        private static final Logger log = LoggerFactory.getLogger(PriceController.class);
        private final GetActualPriceUseCase getActualPriceUseCase;
        private final PriceResponseMapper priceResponseMapper;

        public PriceController(GetActualPriceUseCase getActualPriceUseCase,
            PriceResponseMapper priceResponseMapper) {
            this.getActualPriceUseCase = getActualPriceUseCase;
            this.priceResponseMapper = priceResponseMapper;
        }

        @GetMapping(value = "/date/{date}/productid/{productid}/brandid/{brandid}",
            produces = MediaType.APPLICATION_JSON_VALUE)

        public ResponseEntity<PriceResponse> getActualPrice(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss") LocalDateTime dateTime,
            @PathVariable("productid") Long productId,  @PathVariable("brandid") Long brandId) {
                
            log.info("Received request to get price for productId: {}, brandId: {}, date: {}", productId, brandId, dateTime != null ? dateTime.toString() : "null"  );
            return ResponseEntity.ok(
            priceResponseMapper.toDto(
                getActualPriceUseCase.execute(
                    productId, brandId, dateTime)));

                    
        }

}
