package com.vladislavskiy.Recipe.App.repository;

import com.vladislavskiy.Recipe.App.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();
    User getById(Integer id);
    User findByEmail(final String email);

    User getByEmail(String email);
}
