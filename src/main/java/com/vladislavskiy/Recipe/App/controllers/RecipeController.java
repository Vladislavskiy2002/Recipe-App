package com.vladislavskiy.Recipe.App.controllers;

import com.vladislavskiy.Recipe.App.comparator.ReceptAuthorComparator;
import com.vladislavskiy.Recipe.App.comparator.ReceptNameComparator;
import com.vladislavskiy.Recipe.App.entity.Product;
import com.vladislavskiy.Recipe.App.entity.Recept;
import com.vladislavskiy.Recipe.App.entity.Role;
import com.vladislavskiy.Recipe.App.entity.User;
import com.vladislavskiy.Recipe.App.repository.ProductRepository;
import com.vladislavskiy.Recipe.App.repository.ReceiptRepository;
import com.vladislavskiy.Recipe.App.repository.UserRepository;
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
import java.util.*;

@Slf4j
@Controller
@RequestMapping("/receipt")
public class RecipeController {
    @Autowired
    private ReceiptRepository receiptRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserService userService;

    static String sortOrder = "asc";
    @GetMapping("/myRecipes")
    private ModelAndView getUsersReceipts(Principal principal) {
        String username = principal.getName();
        User user = userService.getByEmail(username);
        List<Recept> recepts = recipeService.findAllByUserId(user.getId());

        Map<String, Object> model = new HashMap<>();
        model.put("Recepts", recepts);
        model.put("sortOrder", sortOrder);
        if(sortOrder.equals("asc")) {
            sortOrder = "desc";
        }
        else sortOrder = "asc";
        return new ModelAndView("my-all-receipts", model);
    }
    @GetMapping("/mySortedByNameRecipes")
    private ModelAndView getSortedByNameReceipts(Principal principal) {
        String username = principal.getName();
        User user = userService.getByEmail(username);
        List<Recept> recepts = recipeService.findAllByUserId(user.getId());
        recipeService.sortRecipes(recepts,new ReceptNameComparator(),sortOrder);

        Map<String, Object> model = new HashMap<>();
        model.put("Recepts", recepts);
        model.put("sortOrder", sortOrder);
        if(sortOrder.equals("asc")) {
            sortOrder = "desc";
        }
        else sortOrder = "asc";
        return new ModelAndView("my-all-receipts", model);
    }
    @GetMapping("/mySortedByAuthorRecipes")
    private ModelAndView getSortedByAuthorMyReceipts(Principal principal) {
        String username = principal.getName();
        User user = userService.getByEmail(username);
        List<Recept> recepts = recipeService.findAllByUserId(user.getId());
        recipeService.sortRecipes(recepts,new ReceptAuthorComparator(),sortOrder);
        Map<String, Object> model = new HashMap<>();
        model.put("Recepts", recepts);
        model.put("sortOrder", sortOrder);
        if(sortOrder.equals("asc")) {
            sortOrder = "desc";
        }
        else sortOrder = "asc";
        return new ModelAndView("my-all-receipts", model);
    }
    @GetMapping("/allRecipes")
    private String getAllReceipts(Model model, Principal principal) {
        String name = principal.getName();
        User user = userService.getByEmail(name);
        List<Role> roles = user.getRoles().stream().toList();
        model.addAttribute("roles", roles);
        List<Recept> recepts = recipeService.findAll();
        model.addAttribute("Recepts", recepts);
        model.addAttribute("sortOrder", sortOrder);
        return "all-receipts";
    }
    @GetMapping("/allSortedByAuthorRecipes")
    private ModelAndView getSortedByAuthorAllReceipts(Principal principal) {
        String username = principal.getName();
        User user = userService.getByEmail(username);
        List<Recept> recepts = recipeService.findAll();
        recipeService.sortRecipes(recepts,new ReceptAuthorComparator(),sortOrder);
        List<Role> roles = user.getRoles().stream().toList();

        Map<String, Object> model = new HashMap<>();
        model.put("Recepts", recepts);
        model.put("roles", roles);
        model.put("sortOrder", sortOrder);
        if(sortOrder.equals("asc")) {
            sortOrder = "desc";
        }
        else sortOrder = "asc";
        return new ModelAndView("all-receipts", model);
    }
    @GetMapping("/allSortedByNameRecipes")
    private ModelAndView getSortedByNameAllReceipts(Principal principal) {
        String username = principal.getName();
        User user = userService.getByEmail(username);
        List<Recept> recepts = recipeService.findAll();
        recipeService.sortRecipes(recepts,new ReceptNameComparator(),sortOrder);
        List<Role> roles = user.getRoles().stream().toList();

        Map<String, Object> model = new HashMap<>();
        model.put("Recepts", recepts);
        model.put("roles", roles);
        model.put("sortOrder", sortOrder);
        if(sortOrder.equals("asc")) {
            sortOrder = "desc";
        }
        else sortOrder = "asc";
        return new ModelAndView("all-receipts", model);
    }
    @GetMapping("/recipes/new")
    public String showNewRecipeForm(Model model) {
        model.addAttribute("recipe", new Recept());
        return "newRecipe";
    }

    @PostMapping("/recipes/new")
    public String createNewRecipe(Principal principal, @ModelAttribute Recept recept) {
        if(recept.getProducts()!=null) {
            Recept recept1 = new Recept();
            List<Product> products = new ArrayList<>();
            for (Product product : recept.getProducts()) {
                if (product.getName() != null && product.getWeight() != null) {
                    product.setRecept(recept1);
                    products.add(product);
                }
            }
            recept1.setProducts(products);
            recept1.setName(recept.getName());
            recept1.setDescription(recept.getDescription());
            recept1.setUser(userService.getByEmail(principal.getName()));
            recipeService.addRecipe(recept1);
        }
        else {
            recept.setProducts(new ArrayList<>());
        }
//            for (Product product : recept.getProducts()) {
//                if (product.getName() != null && product.getWeight() != null) {
//                    product.setRecept(recept);
//                }
//            }
//        }
//        else {
//            recept.setProducts(new ArrayList<>());
//        }
//        recept.setUser(userService.getByEmail(principal.getName()));
//        recipeService.addRecipe(recept);
        return "redirect:/mvc/user";
    }
    @GetMapping("/updateRecipe")
    public ModelAndView showUpdateForm(Recept tempRecept) {
        Recept recept = recipeService.getReceiptById(tempRecept.getId());
        ModelAndView modelAndView = new ModelAndView("recipe-update-form");
        modelAndView.addObject("ProductCount", recept.getProducts().size());
        modelAndView.addObject("recipe", recept);
        modelAndView.addObject("products", recept.getProducts());
        return modelAndView;
    }

    @PostMapping("/recipes/{id}/update")
    public String updateRecipe(@PathVariable Integer id, @ModelAttribute Recept recept,Principal principal) {
        Recept recept1 = recipeService.getReceiptById(id);
        List<Product> products = new ArrayList<>();
        for (Product product : recept.getProducts()) {
            if (product.getName() != null && product.getWeight() != null) {
                product.setRecept(recept1);
                products.add(product);
            }
        }
        productRepository.deleteAll(recept1.getProducts());
        recept1.setProducts(products);
        recept1.setName(recept.getName());
        recept1.setDescription(recept.getDescription());
        recept1.setUser(userService.getByEmail(principal.getName()));
        recipeService.addRecipe(recept1);

        return "redirect:/receipt/myRecipes";
    }
    @GetMapping("/deleteRecipe")
    private String deleteReceipt(Recept recept, Principal principal) {
        recipeService.deleteRecipe(recept);
        receiptRepository.flush();
            return "redirect:/receipt/myRecipes";
    }
    @GetMapping("/allRecipe/deleteRecipe")
    private String deleteFromAllReceipt(Recept recept, Principal principal) {
        recipeService.deleteRecipe(recept);
        receiptRepository.flush();
            return "redirect:/receipt/allRecipes";
    }
    @GetMapping("/detailsRecipe")
    public String showReceiptDetails(Recept tempRecept, Model model) {
        Recept recept = recipeService.getReceiptById(tempRecept.getId());
        List<Product> products = recept.getProducts();
        if(products == null)
        {
            products = new ArrayList<>();
        }
        model.addAttribute("products", products);
        model.addAttribute("recipe", recept);
        return "recipe-details";
    }
    @GetMapping("/allRecipe/detailsRecipe")
    public String showFromAllReceiptDetails(Recept tempRecept, Model model) {

        Recept recept = recipeService.getReceiptById(tempRecept.getId());
        List<Product> products = recept.getProducts();
        if(products == null)
        {
            products = new ArrayList<>();
        }
        model.addAttribute("products", products);
        model.addAttribute("recipe", recept);
        return "all-recipe-details";
    }
    @GetMapping("/receipts/findByName")
    public String findMyReceptsByName()
    {
        return "receipt-name";
    }
    @PostMapping("/receipts/findByName")
    public String findMyReceptsByName(Principal principal, Model model, String name)
    {
        User user = userService.getByEmail(principal.getName());
        List<Recept> recepts = receiptRepository.findAllByUser_IdAndName(user.getId(),name);
        model.addAttribute("sortOrder", sortOrder = "asc");
        model.addAttribute("Recepts", recepts);
        return "my-all-receipts";
    }
    @GetMapping("/allReceipts/findByName")
    public String findAllReceptsByName()
    {
        return "all-receipt-name";
    }
    @PostMapping("/allReceipts/findByName")
    public String findAllReceptsByName(Principal principal, Model model, String name)
    {
        List<Recept> recepts = receiptRepository.findAllByName(name);
        User user = userService.getByEmail(principal.getName());
        List<Role> roles = user.getRoles().stream().toList();
        model.addAttribute("sortOrder", sortOrder = "asc");
        model.addAttribute("Recepts", recepts);
        model.addAttribute("roles", roles);
        return "all-receipts";
    }
    @GetMapping("/receipts/findByProductName")
    public String findReceptByProductName()
    {
        return "product-name";
    }
    @PostMapping("/receipts/findByProductName")
    public String findReceptByProductName(Model model, String name)
    {
        List<Product> products = productRepository.findAllByName(name);
        List<Recept> recepts = new ArrayList<>();
        for(Product product: products)
        {
            recepts.add(product.getRecept());
        }
        model.addAttribute("Recepts", recepts);
        model.addAttribute("sortOrder", sortOrder = "asc");
        return "my-all-receipts";
    }
    @GetMapping("/allReceipts/findByProductName")
    public String findFromAllReceptByProductName()
    {
        return "all-product-name";
    }
    @PostMapping("/allReceipts/findByProductName")
    public String findFromAllReceptByProductName(Principal principal, Model model, String name)
    {
        List<Product> products = productRepository.findAllByName(name);
        List<Recept> recepts = new ArrayList<>();
        for(Product product: products)
        {
            recepts.add(product.getRecept());
        }
        User user = userService.getByEmail(principal.getName());
        List<Role> roles = user.getRoles().stream().toList();
        model.addAttribute("Recepts", recepts);
        model.addAttribute("roles", roles);
        model.addAttribute("sortOrder", sortOrder = "asc");
        return "all-receipts";
    }
    @GetMapping("/receipts/findReceptByAuthorName")
    public String findReceptByAuthor()
    {
        return "author-name";
    }
    @PostMapping("/receipts/findReceptByAuthorName")
    public String findReceptByAuthor(Principal principal, Model model, String name)
    {
        List<User> users = userRepository.findByName(name);
        List<Recept> recepts = new ArrayList<>();
        for(User user: users)
        {
            for(Recept recept: user.getUserRecepts())
            {
                recepts.add(recept);
            }
        }
        User user = userService.getByEmail(principal.getName());
        List<Role> roles = user.getRoles().stream().toList();
        model.addAttribute("Recepts", recepts);
        model.addAttribute("roles", roles);
        model.addAttribute("sortOrder", sortOrder = "asc");
        return "all-receipts";
    }
}
