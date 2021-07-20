package com.labs.indobata.services;

import com.labs.indobata.domain.dto.FeedbackDTO;
import com.labs.indobata.domain.entities.Feedback;
import java.util.List;
import java.util.Optional;

public interface FeedbackService {

    /**
     * Save a Feedback.
     * @param feedbackDTO the entity to save.
     * @return the persisted entity.
     */
    FeedbackDTO save(FeedbackDTO feedbackDTO);

    /**
     * Partially updates a Feedback.
     * @param feedbackDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<FeedbackDTO> partialUpdate(FeedbackDTO feedbackDTO);

    /**
     * Get all the categories.
     * @return the list of entities.
     */
    List<Feedback> findAll();


    /**
     * Get the "id" Feedback.
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FeedbackDTO> findOne(Long id);


    /**
     * Delete the "id" Feedback.
     * @param id the id of the entity.
     */
    void delete(Long id);
}
