package com.vladislavskiy.Recipe.App.dto;

import lombok.Data;

@Data
public class ReceiptDto {
    private Integer userId;
    private String name;
    private String description;
}
