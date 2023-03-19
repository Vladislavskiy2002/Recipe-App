package com.vladislavskiy.Recipe.App.repository;

import com.vladislavskiy.Recipe.App.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
