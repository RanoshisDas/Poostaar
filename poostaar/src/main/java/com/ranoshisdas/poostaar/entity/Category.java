package com.ranoshisdas.poostaar.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer CategoryId;

    private String course;

    @Column(name = "Department")
    private String CategoryTitle;

    @Column(name = "Admission Year")
    private String categoryDescription;

    @OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
    private List<Post> posts;
}
