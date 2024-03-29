package com.ranoshisdas.poostaar.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    private String name;
    private String password;
    private String email;
    private String college;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Post> post;
}
