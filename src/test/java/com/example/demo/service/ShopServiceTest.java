package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepo;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ShopServiceTest {


    @Autowired
    private ShopService shopService;

    @Autowired
    private ProductRepo productRepo;

    public void addProduct() {

        System.out.println("###########addProduct#############");

        Category category1 = new Category();
        category1.setName("category1");
        Category category2 = new Category();
        category2.setName("category2");
        Category category3 = new Category();
        category3.setName("category3");


        Product product1 = new Product("product1", "description1", 3L, 25.4);
        Product product2 = new Product("product2", "description2", 3L, 25.4);
        Product product3 = new Product("product3", "description3", 3L, 25.4);
        Product product4 = new Product("product4", "description4", 3L, 25.4);
        Product product5 = new Product("product5", "description5", 3L, 25.4);

        product1.setCategories(List.of(category1, category2));
        product2.setCategories(List.of(category3, category2));
        product3.setCategories(List.of(category1));
        product4.setCategories(List.of(category2));
        product5.setCategories(List.of(category1, category3));

        productRepo.saveAll(List.of(product1, product2, product3, product4, product5));

    }

    @Test
    void getProduct() {

        System.out.println("###########getProduct#############");


        List<ProductDto> products = shopService.getProduct(List.of("category1", "category3"), "USD");

        System.out.println(products);

        Assertions.assertEquals(4, products.size());



    }
}