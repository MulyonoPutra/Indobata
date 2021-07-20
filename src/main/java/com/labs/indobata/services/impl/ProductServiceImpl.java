package com.labs.indobata.services.impl;

import com.labs.indobata.domain.dto.ProductDTO;
import com.labs.indobata.domain.entities.Product;
import com.labs.indobata.mapper.ProductMapper;
import com.labs.indobata.repositories.ProductRepository;
import com.labs.indobata.services.ProductService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Product}.
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

  private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

  private final ProductRepository productRepository;

  private final ProductMapper productMapper;

  /**
   * A Constructor
   */
  public ProductServiceImpl(
    ProductRepository productRepository,
    ProductMapper productMapper
  ) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
  }

  @Override
  public ProductDTO save(ProductDTO productDTO) {
    log.debug("Request to save Product : {}", productDTO);
    Product product = productMapper.toEntity(productDTO);
    product = productRepository.save(product);
    return productMapper.toDto(product);
  }

  @Override
  public Optional<ProductDTO> partialUpdate(ProductDTO productDTO) {
    log.debug("Request to partially update Product : {}", productDTO);

    return productRepository
      .findById(productDTO.getId())
      .map(
        existingProduct -> {
          productMapper.partialUpdate(existingProduct, productDTO);
          return existingProduct;
        }
      )
      .map(productRepository::save)
      .map(productMapper::toDto);
  }

  @Override
  public List<Product> findAll() {
    log.debug("Request to get all Products");
    return productRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<ProductDTO> findOne(Long id) {
    log.debug("Request to get Product : {}", id);
    return productRepository.findById(id).map(productMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete Product : {}", id);
    productRepository.deleteById(id);
  }
}
