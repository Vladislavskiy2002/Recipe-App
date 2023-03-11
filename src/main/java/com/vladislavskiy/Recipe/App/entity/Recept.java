package com.vladislavskiy.Recipe.App.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "recepts")
@Data
public class Recept {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    private String description;
}
