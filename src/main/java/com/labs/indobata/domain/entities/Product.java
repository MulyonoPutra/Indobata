package com.labs.indobata.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    private String description;

    private String sku;

    private String size;

    private String price;

    @Lob
    @Column(name = "images", nullable = false)
    private byte[] images;

    @Column(name = "images_content_type", nullable = false)
    private String imagesContentType;

    @ManyToOne
    private Category category_product;
}
