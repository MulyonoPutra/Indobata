package com.labs.indobata.domain.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter @Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Clients implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "address", nullable = false)
    private String address;

    @Lob
    @NotNull
    @Column(name = "url", nullable = false)
    private String url;

    @Lob
    @Column(name = "images", nullable = false)
    private byte[] images;

    @Column(name = "images_content_type", nullable = false)
    private String imagesContentType;
}
