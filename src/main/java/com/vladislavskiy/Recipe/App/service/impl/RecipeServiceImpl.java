package com.vladislavskiy.Recipe.App.service.impl;

import com.vladislavskiy.Recipe.App.entity.Recept;
import com.vladislavskiy.Recipe.App.repository.ReceiptRepository;
import com.vladislavskiy.Recipe.App.service.RecipeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private ReceiptRepository repository;

    @Override
    @Transactional
    public Recept getReceiptById(final Integer id) {
        return repository.findById(id).get();
    }

    @Override
    public List<Recept> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Recept> findAllByUserId(Integer id) {
        return repository.findAllByUser_Id(id);
    }

    @Override
    @Transactional
    public void addRecipe(Recept recept) {
        repository.save(recept);
    }

    @Override
    public void deleteRecipe(Recept recept) {
        repository.delete(recept);
    }

    @Override
    public List<Recept> findAllByUser_IdAndName(Integer id, String name) {
        return repository.findAllByUser_IdAndName(id, name);
    }

    @Override
    public List<Recept> findAllByName(final String name) {
        return repository.findAllByName(name);
    }
}
