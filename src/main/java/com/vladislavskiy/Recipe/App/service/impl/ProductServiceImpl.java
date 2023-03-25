package com.vladislavskiy.Recipe.App.service.impl;

import com.vladislavskiy.Recipe.App.entity.Product;
import com.vladislavskiy.Recipe.App.repository.ProductRepository;
import com.vladislavskiy.Recipe.App.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }
}
