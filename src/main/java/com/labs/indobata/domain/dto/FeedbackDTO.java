package com.labs.indobata.domain.dto;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import lombok.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTO implements Serializable {

  private Long id;

  @NotNull
  private String name;

  @NotNull
  private String email;

  @Lob
  @NotNull
  private String messages;
}
