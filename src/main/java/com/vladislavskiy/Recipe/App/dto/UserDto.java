package com.vladislavskiy.Recipe.App.dto;

import com.vladislavskiy.Recipe.App.entity.Recept;
import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private Recept recept;
}
