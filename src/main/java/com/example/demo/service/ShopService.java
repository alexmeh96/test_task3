package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Comment;
import com.example.demo.model.Currency;
import com.example.demo.model.Product;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.repo.CommentRepo;
import com.example.demo.repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ShopService {

    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;
    private final CommentRepo commentRepo;


    public ShopService(CategoryRepo categoryRepo, ProductRepo productRepo, CommentRepo commentRepo) {
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
        this.commentRepo = commentRepo;
    }


    public void addComment(Long productId, String name, String message, Byte rating) {
        Product product = productRepo.getById(productId);
        Comment comment = new Comment(name, message, rating, product);
        commentRepo.save(comment);
    }

    public void buyProduct(Long productId, Long count) {
        Product product = productRepo.getById(productId);
        product.setCount(product.getCount() - count);
        productRepo.save(product);
    }

    public double rateOfCurrency(Currency currencyFrom, Currency currencyTo) {

        // todo: поменять валюту со значением value с currencyFrom в currencyTo

        return 1.5;
    }

    public List<ProductDto> getProduct(List<String> category, String currency) {

        List<Product> products = productRepo.findByCategories_NameIn(category);
        List<ProductDto> productDtos = new ArrayList<>();

        for (Product product: products) {
            Double avgRating = commentRepo.getAverageRating(product);

            ProductDto productDto = new ProductDto();
            if (avgRating != null) {
                productDto.setRating(true);
                productDto.setAvgRating(avgRating);
            }
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setCount(product.getCount());
            productDto.setDescription(product.getDescription());
            productDto.setPrice(product.getPrice());
            productDto.setCurrency(Currency.RUB.name());

            productDtos.add(productDto);
        }


        if (currency != null && Arrays.stream(Currency.values()).map(Enum::name).anyMatch(c -> c.equals(currency))) {
            productDtos.forEach(productDto -> {
                productDto.setPrice(productDto.getPrice() * rateOfCurrency(Currency.RUB, Currency.valueOf(currency)));
                productDto.setCurrency(currency);
            });
        }

        return productDtos;
    }
}
