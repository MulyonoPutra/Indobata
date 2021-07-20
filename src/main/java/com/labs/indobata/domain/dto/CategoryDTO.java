package com.labs.indobata.domain.dto;

import lombok.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
