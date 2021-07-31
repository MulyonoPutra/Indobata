package com.labs.indobata.repositories;

import com.labs.indobata.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.category_product.id LIKE :categoryId")
    public List<Product> findProductByCategory(@PathParam("categoryId") Long categoryId);
}
