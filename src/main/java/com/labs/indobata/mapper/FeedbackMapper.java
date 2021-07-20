package com.labs.indobata.mapper;

import com.labs.indobata.domain.dto.FeedbackDTO;
import com.labs.indobata.domain.entities.Feedback;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {})
public interface FeedbackMapper extends EntityMapper<FeedbackDTO, Feedback> {
  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  Feedback toDtoId(Feedback feedback);
}
