package com.surendiran.ecom.app.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    private String name;

    private String description;

    private BigDecimal price;

    private Integer quantity;

    private String category;

    private String imageUrl;
}
