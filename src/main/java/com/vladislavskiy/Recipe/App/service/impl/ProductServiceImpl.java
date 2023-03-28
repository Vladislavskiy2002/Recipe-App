package com.vladislavskiy.Recipe.App.service.impl;

import com.vladislavskiy.Recipe.App.entity.Product;
import com.vladislavskiy.Recipe.App.entity.Recept;
import com.vladislavskiy.Recipe.App.repository.ProductRepository;
import com.vladislavskiy.Recipe.App.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }


    @Override
    public void deleteAll(List<Product> products) {
        productRepository.deleteAll(products);
    }

    @Override
    public List<Product> findAllByName(String name) {
        return productRepository.findAllByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Product> addProducts(Recept recept, Recept newRecept) {
        List<Product> products = new ArrayList<>();
        for (Product product : recept.getProducts()) {
            if (product.getName() != null && product.getWeight() != null) {
                product.setRecept(newRecept);
                products.add(product);
            }
        }
        return products;
    }
}
