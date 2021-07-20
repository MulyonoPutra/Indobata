package com.labs.indobata.services;

import com.labs.indobata.domain.dto.ProductDTO;
import com.labs.indobata.domain.entities.Product;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.labs.indobata.domain.entities.Product}.
 */
public interface ProductService {

    /**
     * Save a post.
     *
     * @param postDTO the entity to save.
     * @return the persisted entity.
     */
    ProductDTO save(ProductDTO postDTO);


    /**
     * Partially updates a product.
     *
     * @param productDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProductDTO> partialUpdate(ProductDTO productDTO);


    /**
     * Get all the posts.
     *
     * @return the list of entities.
     */
    List<Product> findAll();


    /**
     * Get the "id" post.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductDTO> findOne(Long id);


    /**
     * Delete the "id" post.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
