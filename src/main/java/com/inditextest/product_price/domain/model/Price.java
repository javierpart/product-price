package com.inditextest.product_price.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    private Long brandId;
    private Long productId;
    private Integer tariffId;
    private Double price;
    private Integer priority;
    private String currency;
    private String startDate;
    private String endDate;
}
