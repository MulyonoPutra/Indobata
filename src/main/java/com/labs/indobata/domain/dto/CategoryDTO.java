package com.labs.indobata.domain.dto;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO implements Serializable {

  private Long id;

  @NotNull
  private String name;
}
