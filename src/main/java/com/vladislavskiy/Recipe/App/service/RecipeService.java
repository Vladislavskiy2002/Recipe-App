package com.vladislavskiy.Recipe.App.service;

import com.vladislavskiy.Recipe.App.dto.ReceiptDto;
import com.vladislavskiy.Recipe.App.dto.UserDto;
import com.vladislavskiy.Recipe.App.entity.Recept;
import com.vladislavskiy.Recipe.App.entity.User;
import com.vladislavskiy.Recipe.App.repository.ReceiptRepository;

import java.util.List;

public interface RecipeService {
    Recept getReceiptById(final Integer id);
    List<Recept> findAll();
    List<Recept> findAllByUserId(Integer id);
    void addRecipe(Recept recept);

    void deleteRecipe(Recept recept);
}
