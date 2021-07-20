package com.labs.indobata.domain.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {

    private Long id;

    private String productName;

    private String description;

    private String sku;

    private String size;

    private String price;

    @Lob
    private byte[] images;

    private String imagesContentType;

    private CategoryDTO category_product;
}
