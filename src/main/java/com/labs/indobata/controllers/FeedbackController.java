package com.labs.indobata.controllers;

import com.labs.indobata.domain.dto.FeedbackDTO;
import com.labs.indobata.domain.entities.Feedback;
import com.labs.indobata.exceptions.BadException;
import com.labs.indobata.services.FeedbackService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

  private final Logger log = LoggerFactory.getLogger(FeedbackController.class);

  private final FeedbackService feedbackService;

  public FeedbackController(FeedbackService feedbackService) {
    this.feedbackService = feedbackService;
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
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the postDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/{id}")
  public ResponseEntity<FeedbackDTO> findById(@PathVariable Long id) {
    log.debug("REST request to get Card : {}", id);
    Optional<FeedbackDTO> feedback = feedbackService.findOne(id);
    return new ResponseEntity(feedback, HttpStatus.OK);
  }
}
