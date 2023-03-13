package com.vladislavskiy.Recipe.App.controllers;

import com.vladislavskiy.Recipe.App.dto.ReceiptDto;
import com.vladislavskiy.Recipe.App.entity.Recept;
import com.vladislavskiy.Recipe.App.entity.User;
import com.vladislavskiy.Recipe.App.security.details.UserDetailsImpl;
import com.vladislavskiy.Recipe.App.service.RecipeService;
import com.vladislavskiy.Recipe.App.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
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
    @Autowired
    private UserService userService;
    @GetMapping("/myRecipes")
    private String getUsersReceipts(@RequestParam(required = false) String sort,Model model)
    {
        User user = userService.getAuthUser();
        List<Recept> recepts = recipeService.findAllByUserId(user.getId());
        if (sort != null) {
            if (sort.equals("asc-name")) {
                // sort products by name from A to Z
                recepts.sort(Comparator.comparing(Recept::getName));
            } else if (sort.equals("desc-name")) {
                // sort products by name from Z to A
                recepts.sort(Comparator.comparing(Recept::getName).reversed());
            }
        }
        model.addAttribute("Recepts", recepts);
        return "myAllReceipts_page";
    }
    @GetMapping("/allRecipes")
    private String getAllReceipts(Model model) {
        List<Recept> recepts = recipeService.findAll();
        model.addAttribute("Recepts", recepts);
        return "AllReceipts_page";
    }
    @GetMapping("/addRecipe")
    private String getAddReceipt()
    {
        return "addRecipePage";
    }
    @PostMapping("/addRecipe")
    private String addReceipt(Recept recept)
    {
        System.out.println(recept.getId());
        if(recept.getUser()==null)
        {
            User user = userService.getAuthUser();
            recept.setUser(user);
        }
        recipeService.addRecipe(recept);
        return "redirect:/user";
    }
    @GetMapping("/updateRecipe")
    private String updateReceipt(Recept recept, Model model)
    {
        System.out.println("IDDDDDDDDDDDDDDDDDDDDDD"+ recept.getId());
        model.addAttribute("Recept", recept);
        return "updateRecipePage";
    }
    @GetMapping("/deleteRecipe")
    private String deleteReceipt(Recept recept)
    {
        recipeService.deleteRecipe(recept);
        return "redirect:/receipt/myRecipes";
    }
//    @PostMapping("/updateRecipe")
//    private String updateReceipt(Recept recept)
//    {
//        User user = userService.getAuthUser();
//        return "redirect:/user";
//    }
}
