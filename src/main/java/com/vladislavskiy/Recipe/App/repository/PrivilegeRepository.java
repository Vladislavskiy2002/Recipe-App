package com.vladislavskiy.Recipe.App.repository;

import com.vladislavskiy.Recipe.App.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
    Privilege findByName(String name);
}
