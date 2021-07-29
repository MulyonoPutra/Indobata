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
public class ClientsDTO implements Serializable  {

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
