package com.vladislavskiy.Recipe.App.repository;

import com.vladislavskiy.Recipe.App.entity.Recept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceiptRepository extends JpaRepository<Recept, Integer> {

    Optional<Recept> findById(Integer id);

    List<Recept> findAllByUser_Id(Integer id);

    List<Recept> findAll();

    void delete(Recept recept);

    List<Recept> findAllByUserId(Integer id);

    List<Recept> findAllByName(String name);

    List<Recept> findAllReceptsByProductsOrderByName(String name);

    List<Recept> findAllByUser_IdAndName(Integer id, String name);
}
