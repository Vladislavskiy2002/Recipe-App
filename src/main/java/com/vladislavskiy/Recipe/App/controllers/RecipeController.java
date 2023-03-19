package com.vladislavskiy.Recipe.App.controllers;

import com.vladislavskiy.Recipe.App.entity.Product;
import com.vladislavskiy.Recipe.App.entity.Recept;
import com.vladislavskiy.Recipe.App.entity.Role;
import com.vladislavskiy.Recipe.App.entity.User;
import com.vladislavskiy.Recipe.App.form.ProductForm;
import com.vladislavskiy.Recipe.App.form.RecipeForm;
import com.vladislavskiy.Recipe.App.repository.ReceiptRepository;
import com.vladislavskiy.Recipe.App.service.ProductService;
import com.vladislavskiy.Recipe.App.service.RecipeService;
import com.vladislavskiy.Recipe.App.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/receipt")
public class RecipeController {
    @Autowired
    private ReceiptRepository receiptRepository;

    {
        System.out.println("+++++RECIPE CONTROLLER HAS BEEN STARTED+++++");
    }

    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @GetMapping("/myRecipes")
    private String getUsersReceipts(@RequestParam(value = "sortBy", defaultValue = "id") String sortBy
            , @RequestParam(value = "sortOrder", defaultValue = "asc") String sortOrder, Principal principal, Model model) {
        String username = principal.getName();
        User user = userService.getByEmail(username);
        List<Recept> recepts = recipeService.findAllByUserId(user.getId());
        if (sortBy.equals("name")) {
            Comparator<Recept> comparator = Comparator.comparing(Recept::getName);
            if (sortOrder.equals("desc")) {
                comparator = comparator.reversed();
            }
            Collections.sort(recepts, comparator);
        }
        model.addAttribute("Recepts", recepts);
        return "my-all-receipts";
    }

    @GetMapping("/allRecipes")
    private String getAllReceipts(Model model, Principal principal) {
        String name = principal.getName();
        User user = userService.getByEmail(name);
        List<Role> roles = user.getRoles().stream().toList();
        model.addAttribute("roles", roles);
        List<Recept> recepts = recipeService.findAll();
        model.addAttribute("Recepts", recepts);
        return "all-receipts";
    }
    @PostMapping("/saveRecipe")
    public String saveRecipe(Principal principal, @ModelAttribute RecipeForm recipeForm) {
        Recept recept = new Recept();
        recept.setName(recipeForm.getName());
        recept.setDescription(recipeForm.getDescription());

        List<Product> products = new ArrayList<>();
        for (ProductForm productForm : recipeForm.getProducts()) {
            Product product = new Product();
            product.setName(productForm.getName());
            product.setWeight(productForm.getWeight());
            products.add(product);
        }
        recept.setProducts(products);
        for (Product product : products) {
            System.out.println("PRODUCT NAME: " + product.getName());
        }

        if (recept.getUser() == null) {
            String username = principal.getName();
            User user = userService.getByEmail(username);
            recept.setUser(user);
        }

        receiptRepository.save(recept);

        return "redirect:/receipt/myRecipes";
    }
    @GetMapping("/recipes/new")
    public String showRecipeForm(Model model) {
        RecipeForm recipeForm = new RecipeForm();
        model.addAttribute("recipeForm", recipeForm);
        model.addAttribute("productCount", 1);
        return "recipe-form";
    }

    @GetMapping("/updateRecipe")
    public ModelAndView showUpdateForm(Recept tempRecept) {
        Recept recept = recipeService.getReceiptById(tempRecept.getId());
        ModelAndView modelAndView = new ModelAndView("recipe-update-form");
        modelAndView.addObject("productCount", recept.getProducts().size());
        modelAndView.addObject("recipe", recept);
        return modelAndView;
    }

    @PostMapping("/updateRecipe/{id}")
    public String updateRecipe(@ModelAttribute Recept recept, @PathVariable Integer id, Principal principal) {
        System.out.println("UPRECIPE");
        System.out.println(recept.getName());
        System.out.println(recept.getId());
        Recept recept1 = recipeService.getReceiptById(id);
        if (recept.getUser() == null) {
            String username = principal.getName();
            User user = userService.getByEmail(username);
            recept1.setName(recept.getName());
            recept1.setDescription(recept.getDescription());
            recept1.setProducts(recept.getProducts());
            recept1.setUser(user);
        }
        recipeService.addRecipe(recept1);

        return "redirect:/receipt/myRecipes";
    }
    @GetMapping("/deleteRecipe")
    private String deleteReceipt(Recept recept, Principal principal) {
        recipeService.deleteRecipe(recept);
        boolean role = userService.getByEmail(principal.getName()).getRoles().stream().anyMatch(s -> s.getName().equals("ROLE_ADMIN"));
        if (!role) {
            return "redirect:/receipt/myRecipes";
        } else {
            return "redirect:/receipt/allRecipes";
        }
    }
}
