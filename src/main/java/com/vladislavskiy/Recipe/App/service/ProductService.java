package com.vladislavskiy.Recipe.App.service;

import com.vladislavskiy.Recipe.App.entity.Product;
import com.vladislavskiy.Recipe.App.entity.Recept;

import java.util.List;

public interface ProductService {
    Product save(Product product);

    void deleteAll(List<Product> products);

    List<Product> findAllByName(String name);

    List<Product> addProducts(Recept recept, Recept newRecept);
}
