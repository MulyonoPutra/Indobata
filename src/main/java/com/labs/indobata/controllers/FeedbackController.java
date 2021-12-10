package com.labs.indobata.controllers;

import static com.labs.indobata.constants.ResponseConstants.DELETED_SUCCESSFULLY;

import com.labs.indobata.domain.dto.FeedbackDTO;
import com.labs.indobata.domain.dto.ResponseMessages;
import com.labs.indobata.domain.entities.Feedback;
import com.labs.indobata.exceptions.BadException;
import com.labs.indobata.repositories.FeedbackRepository;
import com.labs.indobata.services.FeedbackService;
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

@CrossOrigin("http://localhost:4200/, http://localhost:4300/*")
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

  private static final String ENTITY_NAME = "feedback";

  private final Logger log = LoggerFactory.getLogger(FeedbackController.class);

  private final FeedbackService feedbackService;

  private final FeedbackRepository feedbackRepository;

  public FeedbackController(
    FeedbackService feedbackService,
    FeedbackRepository feedbackRepository
  ) {
    this.feedbackService = feedbackService;
    this.feedbackRepository = feedbackRepository;
  }

  /**
   * {@code Feedback /feedback} : Create a new feedback.
   *
   * @param feedbackDTO the feedbackDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
   *         body the new cardDTO, or with status {@code 400 (Bad Request)} if the
   *         post has already an ID.
   * @throws BadException if the Location URI syntax is incorrect.
   */
  @PostMapping
  public ResponseEntity<FeedbackDTO> create(
    @Valid @RequestBody FeedbackDTO feedbackDTO
  )
    throws BadException {
    log.debug("REST request to save Card : {}", feedbackDTO);
    if (feedbackDTO.getId() != null) {
      throw new BadException("Error!");
    }
    FeedbackDTO result = feedbackService.save(feedbackDTO);
    return ResponseEntity.ok(result);
  }

  /**
   * {@code GET /feedback} : get all the feedback.
   *
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
   *         of cards in body.
   */
  @GetMapping
  public ResponseEntity<List<Feedback>> findAllFeedback() {
    List<Feedback> feedback = feedbackService.findAll();
    return new ResponseEntity<>(feedback, HttpStatus.OK);
  }

  /**
   * {@code GET /feedback/:id} : get the "id" feedback.
   *
   * @param id the id of the postDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
   *         the postDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/{id}")
  public ResponseEntity<FeedbackDTO> findById(@PathVariable Long id) {
    log.debug("REST request to get Card : {}", id);
    Optional<FeedbackDTO> feedback = feedbackService.findOne(id);
    return new ResponseEntity(feedback, HttpStatus.OK);
  }

  /**
   * {@code DELETE  /feedback/:id} : delete the "id" feedback.
   *
   * @param id the id of the feedbackDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseMessages> deleteFeedback(
    @PathVariable Long id
  ) {
    log.debug("REST request to delete Category : {}", id);
    feedbackService.delete(id);
    return ResponseUtils.response(HttpStatus.OK, DELETED_SUCCESSFULLY);
  }

  /**
   * {@code PUT  /feedback/:id} : Updates an existing feedback.
   *
   * @param id the id of the feedbackDTO to save.
   * @param feedbackDTO the feedbackDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
   *         the updated feedbackDTO, or with status {@code 400 (Bad Request)} if
   *         the feedbackDTO is not valid, or with status
   *         {@code 500 (Internal Server Error)} if the feedbackDTO couldn't be
   *         updated.
   * @throws BadException if the Location URI syntax is incorrect.
   */
  @PutMapping("/{id}")
  public ResponseEntity<FeedbackDTO> updateFeedback(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody FeedbackDTO feedbackDTO
  )
    throws BadException {
    log.debug("REST request to update Category : {}, {}", id, feedbackDTO);
    if (feedbackDTO.getId() == null) {
      throw new BadException("Invalid id");
    }
    if (!Objects.equals(id, feedbackDTO.getId())) {
      throw new BadException("Invalid ID");
    }

    if (!feedbackRepository.existsById(id)) {
      throw new BadException("Entity not found");
    }

    FeedbackDTO result = feedbackService.save(feedbackDTO);
    return ResponseEntity
      .ok()
      .headers(
        HeaderUtil.createEntityUpdateAlert(
          ENTITY_NAME,
          feedbackDTO.getId().toString()
        )
      )
      .body(result);
  }
}
