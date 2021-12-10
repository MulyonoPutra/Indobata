package com.labs.indobata.controllers;

import static com.labs.indobata.constants.ResponseConstants.DELETED_SUCCESSFULLY;

import com.labs.indobata.domain.dto.CategoryDTO;
import com.labs.indobata.domain.dto.ResponseMessages;
import com.labs.indobata.domain.entities.Category;
import com.labs.indobata.exceptions.BadException;
import com.labs.indobata.repositories.CategoryRepository;
import com.labs.indobata.services.CategoryService;
import com.labs.indobata.utils.HeaderUtil;
import com.labs.indobata.utils.ResponseUtils;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200/, http://localhost:4300/*")
@RestController
@RequestMapping("/api/category")
public class CategoryController {

  private final Logger log = LoggerFactory.getLogger(CategoryController.class);

  private final CategoryService categoryService;

  private static final String ENTITY_NAME = "category";

  private final CategoryRepository categoryRepository;

  public CategoryController(
    CategoryService categoryService,
    CategoryRepository categoryRepository
  ) {
    this.categoryService = categoryService;
    this.categoryRepository = categoryRepository;
  }

  /**
   * {@code POST  /category} : Create a new category.
   *
   * @param categoryDTO the categoryDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categoryDTO, or with status {@code 400 (Bad Request)} if the category has already an ID.
   * @throws BadException if the Location URI syntax is incorrect.
   */
  @PostMapping
  public ResponseEntity<CategoryDTO> createPost(
    @Valid @RequestBody CategoryDTO categoryDTO
  )
    throws BadException {
    log.debug("REST request to save Category : {}", categoryDTO);
    if (categoryDTO.getId() != null) {
      throw new BadException("Error!");
    }
    CategoryDTO result = categoryService.save(categoryDTO);
    return ResponseEntity.ok(result);
  }

  /**
   * {@code GET  /category} : get all the categories.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categories in body.
   */
  @GetMapping
  public ResponseEntity<List<Category>> findAllCategory() {
    List<Category> categories = categoryService.findAll();
    return new ResponseEntity<>(categories, HttpStatus.OK);
  }

  /**
   * {@code GET  /category/:id} : get the "id" category.
   *
   * @param id the id of the categoryDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categoryDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/{id}")
  public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
    log.debug("REST request to get Category : {}", id);
    Optional<CategoryDTO> categoryDTO = categoryService.findOne(id);
    return new ResponseEntity(categoryDTO, HttpStatus.OK);
  }

  /**
   * {@code DELETE  /category/:id} : delete the "id" category.
   *
   * @param id the id of the categoryDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseMessages> deleteCategory(
    @PathVariable Long id
  ) {
    log.debug("REST request to delete Category : {}", id);
    categoryService.delete(id);
    return ResponseUtils.response(HttpStatus.OK, DELETED_SUCCESSFULLY);
  }

  /**
   * {@code PUT  /categories/:id} : Updates an existing category.
   *
   * @param id the id of the categoryDTO to save.
   * @param categoryDTO the categoryDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoryDTO,
   * or with status {@code 400 (Bad Request)} if the categoryDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the categoryDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/{id}")
  public ResponseEntity<CategoryDTO> updateCategory(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody CategoryDTO categoryDTO
  )
    throws BadException {
    log.debug("REST request to update Category : {}, {}", id, categoryDTO);
    if (categoryDTO.getId() == null) {
      throw new BadException("Invalid id");
    }
    if (!Objects.equals(id, categoryDTO.getId())) {
      throw new BadException("Invalid ID");
    }

    if (!categoryRepository.existsById(id)) {
      throw new BadException("Entity not found");
    }

    CategoryDTO result = categoryService.save(categoryDTO);
    return ResponseEntity
      .ok()
      .headers(
        HeaderUtil.createEntityUpdateAlert(
          ENTITY_NAME,
          categoryDTO.getId().toString()
        )
      )
      .body(result);
  }
}
