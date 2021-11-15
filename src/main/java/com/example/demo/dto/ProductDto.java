package com.example.demo.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Long count;
    private Double price;
    private Double avgRating;
    private boolean isRating;
    private String currency;
}
