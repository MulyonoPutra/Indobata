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
public class ClientsDTO {

  private Long id;

  @NotNull
  private String name;

  private String address;

  @Lob
  @NotNull
  private String url;

  @Lob
  private byte[] images;

  private String imagesContentType;
}
