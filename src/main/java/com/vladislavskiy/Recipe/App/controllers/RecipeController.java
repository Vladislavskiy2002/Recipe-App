package com.vladislavskiy.Recipe.App.controllers;

import com.vladislavskiy.Recipe.App.dto.ReceiptDto;
import com.vladislavskiy.Recipe.App.entity.Recept;
import com.vladislavskiy.Recipe.App.service.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receipt")
public class RecipeController {
    {
        System.out.println("+++++RECIPE CONTROLLER HAS BEEN STARTED+++++");
    }
    @Autowired
    private RecipeService recipeService;
    @GetMapping("/{id}")
    private Recept getReceptById(@PathVariable final Integer id)
    {
        System.out.println("sdfsdfsdfsdsfdfsdsfdfs");
        return recipeService.getReceiptById(id);
    }
    @GetMapping("/all")
    private List<Recept> getAllReceipt()
    {
        System.out.println("sdfsdfsdfsdsfdfsdsfdfs");
        return recipeService.findAll();
    }
    @PostMapping("/add")
    private void addReceiptForUser(@RequestBody ReceiptDto receiptDto)
    {
        recipeService.addReceiptForCurrentUser(receiptDto);
    }
}
