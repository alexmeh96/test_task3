package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String message;
    private Byte rating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    public Comment(String name, String message, Byte rating, Product product) {
        this.name = name;
        this.message = message;
        this.rating = rating;
        this.product = product;
    }
}
