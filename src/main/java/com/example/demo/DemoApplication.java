package com.example.demo;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.repo.ProductRepo;
import com.example.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Autowired
    private ShopService shopService;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("###########addProduct#############");

        Category category1 = new Category();
        category1.setName("category1");
        category1 = categoryRepo.save(category1);

        Category category2 = new Category();
        category2.setName("category2");
        category2 = categoryRepo.save(category2);

        Category category3 = new Category();
        category3.setName("category3");
        category3 = categoryRepo.save(category3);

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
}
