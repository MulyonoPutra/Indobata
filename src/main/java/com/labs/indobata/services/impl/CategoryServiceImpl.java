package com.labs.indobata.services.impl;

import com.labs.indobata.domain.dto.CategoryDTO;
import com.labs.indobata.domain.entities.Category;
import com.labs.indobata.mapper.CategoryMapper;
import com.labs.indobata.repositories.CategoryRepository;
import com.labs.indobata.services.CategoryService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Category}.
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

  private final Logger log = LoggerFactory.getLogger(CategoryServiceImpl.class);

  private final CategoryRepository categoryRepository;

  private final CategoryMapper categoryMapper;

  /**
   * A Constructor
   */
  public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
    this.categoryRepository = categoryRepository;
    this.categoryMapper = categoryMapper;
  }

  @Override
  public CategoryDTO save(CategoryDTO categoryDTO) {
    log.debug("Request to save Category : {}", categoryDTO);
    Category category = categoryMapper.toEntity(categoryDTO);
    category = categoryRepository.save(category);
    return categoryMapper.toDto(category);
  }

  @Override
  public Optional<CategoryDTO> partialUpdate(CategoryDTO categoryDTO) {
    log.debug("Request to partially update Category : {}", categoryDTO);

    return categoryRepository.findById(categoryDTO.getId()).map(existingCategory -> {
      categoryMapper.partialUpdate(existingCategory, categoryDTO);
      return existingCategory;
    }).map(categoryRepository::save).map(categoryMapper::toDto);
  }

  @Override
  public List<Category> findAll() {
    log.debug("Request to get all Categorys");
    return categoryRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<CategoryDTO> findOne(Long id) {
    log.debug("Request to get Category : {}", id);
    return categoryRepository.findById(id).map(categoryMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete Category : {}", id);
    categoryRepository.deleteById(id);
  }
}
