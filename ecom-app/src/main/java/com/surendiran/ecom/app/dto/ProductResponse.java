package com.surendiran.ecom.app.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer quantity;

    private String category;

    private String imageUrl;

    private Boolean active = true;
}
