package com.vladislavskiy.Recipe.App.dto;

import lombok.Data;

@Data
public class ReceiptDto {
    private String name;
    private String description;
    private String productName;
    private Integer weight;
}
