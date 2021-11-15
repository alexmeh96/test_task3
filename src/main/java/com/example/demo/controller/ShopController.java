package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.RequestDto;
import com.example.demo.service.ShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping("/comment/add")
    public ResponseEntity<?> addComment(@RequestBody RequestDto requestDto) {

        try {
            shopService.addComment(requestDto.getProductId(), requestDto.getName(), requestDto.getMessage(), requestDto.getRating());
            return ResponseEntity.status(200).build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(503).build();
        }

    }

    @GetMapping("/product/get")
    public ResponseEntity<?> getProduct(@RequestParam List<String> category,
                                        @RequestParam(required = false) String currency) {

        try {
            List<ProductDto> products = shopService.getProduct(category, currency);
            return new ResponseEntity<>(products, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(503).build();
        }

    }

    @PostMapping("/product/buy")
    public ResponseEntity<?> buyProduct(@RequestBody RequestDto requestDto) {

        try {
            shopService.buyProduct(requestDto.getProductId(), requestDto.getCount());
            return ResponseEntity.status(200).build();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(503).build();
        }

    }
}
