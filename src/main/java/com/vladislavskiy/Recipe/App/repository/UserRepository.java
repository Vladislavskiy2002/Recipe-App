package com.vladislavskiy.Recipe.App.repository;

import com.vladislavskiy.Recipe.App.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();

    User findByEmail(final String email);

    Optional<User> findById(Integer id);

    List<User> findByName(String name);

    void delete(User user);

    List<User> findAllByNameContainingIgnoreCase(String name);
}
