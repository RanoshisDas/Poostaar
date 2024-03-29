package com.ranoshisdas.poostaar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ImageData")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;

    @Lob
    @Column(name = "imagedata",length = 10000000)
    private byte[] imageData;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Post post;
}
