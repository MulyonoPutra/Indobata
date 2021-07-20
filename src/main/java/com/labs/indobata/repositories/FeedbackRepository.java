package com.labs.indobata.repositories;

import com.labs.indobata.domain.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {}
