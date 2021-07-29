package com.labs.indobata.controllers;

import static com.labs.indobata.constants.ResponseConstants.DELETED_SUCCESSFULLY;

import com.labs.indobata.domain.dto.ProductDTO;
import com.labs.indobata.domain.dto.ResponseMessages;
import com.labs.indobata.domain.entities.Product;
import com.labs.indobata.exceptions.BadException;
import com.labs.indobata.repositories.ProductRepository;
import com.labs.indobata.services.ProductService;
import com.labs.indobata.utils.HeaderUtil;
import com.labs.indobata.utils.ResponseUtils;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing {@link com.labs.indobata.domain.entities.Product}.
 */
@RestController
@RequestMapping("/api/product")
public class ProductController {

  private static final String ENTITY_NAME = "product";

  private final Logger log = LoggerFactory.getLogger(ProductController.class);

  private final ProductService productService;

  private final ProductRepository productRepository;

  public ProductController(
    ProductService productService,
    ProductRepository productRepository
  ) {
    this.productService = productService;
    this.productRepository = productRepository;
  }

  /**
   * {@code POST  /products} : Create a new product.
   *
   * @param productDTO the productDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
   *         body the new productDTO, or with status {@code 400 (Bad Request)} if the
   *         product has already an ID.
   * @throws BadException if the Location URI syntax is incorrect.
   */
  @PostMapping
  public ResponseEntity<ProductDTO> createProduct(
    @Valid @RequestBody ProductDTO productDTO
  )
    throws BadException {
    log.debug("REST request to save Product : {}", productDTO);
    if (productDTO.getId() != null) {
      throw new BadException("Error!");
    }
    ProductDTO result = productService.save(productDTO);
    return ResponseEntity.ok(result);
  }

  /**
   * {@code GET  /products} : get all the products.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
   * of products in body.
   */
  @GetMapping
  public ResponseEntity<List<Product>> findAllProduct() {
    List<Product> product = productService.findAll();
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  /**
   * {@code GET  /product/:id} : get the "id" product.
   *
   * @param id the id of the productDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/{id}")
  public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
    log.debug("REST request to get Product : {}", id);
    Optional<ProductDTO> product = productService.findOne(id);
    return new ResponseEntity(product, HttpStatus.OK);
  }

  /**
   * {@code DELETE  /product/:id} : delete the "id" product.
   *
   * @param id the id of the productDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseMessages> deleteProduct(@PathVariable Long id) {
    log.debug("REST request to delete Product : {}", id);
    productService.delete(id);
    return ResponseUtils.response(HttpStatus.OK, DELETED_SUCCESSFULLY);
  }

  /**
   * {@code PUT  /product/:id} : Updates an existing product.
   *
   * @param id the id of the productDTO to save.
   * @param productDTO the productDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productDTO,
   * or with status {@code 400 (Bad Request)} if the productDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the productDTO couldn't be updated.
   * @throws BadException if the Location URI syntax is incorrect.
   */
  @PutMapping("/{id}")
  public ResponseEntity<ProductDTO> updateProduct(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody ProductDTO productDTO
  )
    throws BadException {
    log.debug("REST request to update Category : {}, {}", id, productDTO);
    if (productDTO.getId() == null) {
      throw new BadException("Invalid id");
    }
    if (!Objects.equals(id, productDTO.getId())) {
      throw new BadException("Invalid ID");
    }

    if (!productRepository.existsById(id)) {
      throw new BadException("Entity not found");
    }

    ProductDTO result = productService.save(productDTO);
    return ResponseEntity
      .ok()
      .headers(
        HeaderUtil.createEntityUpdateAlert(
          ENTITY_NAME,
          productDTO.getId().toString()
        )
      )
      .body(result);
  }
  /**
   * {@code GET  /product/search/category/:categoryId} : find the "id" category.
   *
   * @param categoryId the id of the productDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  //    @GetMapping("/search/category/{categoryId}")
  //    public List<Product> getProductByCategory(@PathVariable("categoryId") Long categoryId) {
  //        return productService.findProductByCategoryId(categoryId);
  //    }

}
