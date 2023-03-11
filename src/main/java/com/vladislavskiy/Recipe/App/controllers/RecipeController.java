package com.vladislavskiy.Recipe.App.controllers;

import com.vladislavskiy.Recipe.App.dto.ReceiptDto;
import com.vladislavskiy.Recipe.App.entity.Recept;
import com.vladislavskiy.Recipe.App.entity.User;
import com.vladislavskiy.Recipe.App.service.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
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
    @GetMapping("/myRecipes")
    private String getUsersReceipts(User user, Model model)
    {
        log.info("MYRECEIPT!!!!!!!!!!!!!!!!");
        List<Recept> recepts = recipeService.findAllByUserId(user.getId());
        System.out.println("USER ID: " + user.getId());
        for(Recept recept : recepts)
        {
            System.out.println(recept);
        }
        model.addAttribute("Recepts", recepts);
        return "myAllReceipts_page";
    }
    @PostMapping("/add")
    private void addReceiptForUser(@RequestBody ReceiptDto receiptDto)
    {
        recipeService.addReceiptForCurrentUser(receiptDto);
    }
}
