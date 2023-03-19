package com.vladislavskiy.Recipe.App.form;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RecipeForm {
    private String name;
    private String description;
    private List<ProductForm> products = new ArrayList<>();

    // getters and setters
}

