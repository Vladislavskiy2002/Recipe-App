package com.vladislavskiy.Recipe.App.service;

import com.vladislavskiy.Recipe.App.entity.Recept;

import java.util.List;

public interface RecipeService {
    Recept getReceiptById(final Integer id);

    List<Recept> findAll();

    List<Recept> findAllByUserId(Integer id);

    void addRecipe(Recept recept);

    void deleteRecipe(Recept recept);

    List<Recept> findAllByUser_IdAndName(Integer id, String name);

    List<Recept> findAllByName(final String name);

    List<Recept> findAllReceptsByProductName(final String name);
}
