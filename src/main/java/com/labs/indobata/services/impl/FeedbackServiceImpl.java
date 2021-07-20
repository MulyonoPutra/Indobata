package com.labs.indobata.services.impl;

import com.labs.indobata.domain.dto.FeedbackDTO;
import com.labs.indobata.domain.entities.Feedback;
import com.labs.indobata.mapper.FeedbackMapper;
import com.labs.indobata.repositories.FeedbackRepository;
import com.labs.indobata.services.FeedbackService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Feedback}.
 */
@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

  private final Logger log = LoggerFactory.getLogger(FeedbackServiceImpl.class);

  private final FeedbackRepository feedbackRepository;

  private final FeedbackMapper feedbackMapper;

  /**
   * A Constructor
   */
  public FeedbackServiceImpl(
    FeedbackRepository feedbackRepository,
    FeedbackMapper feedbackMapper
  ) {
    this.feedbackRepository = feedbackRepository;
    this.feedbackMapper = feedbackMapper;
  }

  @Override
  public FeedbackDTO save(FeedbackDTO feedbackDTO) {
    log.debug("Request to save Feedback : {}", feedbackDTO);
    Feedback feedback = feedbackMapper.toEntity(feedbackDTO);
    feedback = feedbackRepository.save(feedback);
    return feedbackMapper.toDto(feedback);
  }

  @Override
  public Optional<FeedbackDTO> partialUpdate(FeedbackDTO feedbackDTO) {
    log.debug("Request to partially update Feedback : {}", feedbackDTO);

    return feedbackRepository
      .findById(feedbackDTO.getId())
      .map(
        existingFeedback -> {
          feedbackMapper.partialUpdate(existingFeedback, feedbackDTO);
          return existingFeedback;
        }
      )
      .map(feedbackRepository::save)
      .map(feedbackMapper::toDto);
  }

  @Override
  public List<Feedback> findAll() {
    log.debug("Request to get all Feedback");
    return feedbackRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<FeedbackDTO> findOne(Long id) {
    log.debug("Request to get Feedback : {}", id);
    return feedbackRepository.findById(id).map(feedbackMapper::toDto);
  }

  @Override
  public void delete(Long id) {
    log.debug("Request to delete Feedback : {}", id);
    feedbackRepository.deleteById(id);
  }
}
