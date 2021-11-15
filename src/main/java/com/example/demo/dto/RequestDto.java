package com.example.demo.dto;

import lombok.Data;

@Data
public class RequestDto {

    private Long commentId;
    private Long productId;
    private String name;
    private String message;
    private Byte rating;

    private Long count;

}
