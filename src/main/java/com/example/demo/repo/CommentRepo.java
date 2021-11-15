package com.example.demo.repo;

import com.example.demo.model.Comment;
import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

    @Query("SELECT AVG(c.rating) FROM Comment c WHERE c.product = ?1")
    Double getAverageRating(Product product);

}
