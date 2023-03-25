package com.vladislavskiy.Recipe.App.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "recepts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recept{
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    private String description;
@OneToMany(mappedBy = "recept", cascade = CascadeType.ALL)
private List<Product> products;
    @Override
    public String toString() {
        return "Recept{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void removeAllProducts()
    {
        products = new ArrayList<>();
    }

}
