package com.labs.indobata.domain.dto;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTO {

  private Long id;

  @NotNull
  private String name;

  @NotNull
  private String email;

  @Lob
  @NotNull
  private String messages;
}
