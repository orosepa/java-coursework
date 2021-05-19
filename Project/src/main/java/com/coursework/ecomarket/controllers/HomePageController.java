package com.coursework.ecomarket.controllers;

import com.coursework.ecomarket.models.Category;
import com.coursework.ecomarket.models.User;
import com.coursework.ecomarket.services.CategoryService;
import com.coursework.ecomarket.services.ProductService;
import com.coursework.ecomarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class HomePageController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;


    @GetMapping({"index", "/"})
    public String index(Model model) {
        model.addAttribute("categoryList", categoryService.listCategory());
        model.addAttribute("productList", productService.listProduct());
        return "index";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("signup")
    public ModelAndView signup(User user) {
        ModelAndView mv = new ModelAndView("/index");
        userService.save(user);
        mv.addObject("productList", productService.listProduct());
        mv.addObject("categoryList", categoryService.listCategory());
        return mv;
    }


    @GetMapping("all")
    public String allProducts(Model model) {
        model.addAttribute("productList", productService.listProduct());
        model.addAttribute("categoryList", categoryService.listCategory());
        return "index";
    }

    @GetMapping("products/{categoryId}")
    public ModelAndView productsByCategory(@PathVariable("categoryId") String categoryId) {
        ModelAndView mv = new ModelAndView("index");
        long lCategoryId = Long.parseLong(categoryId);
        mv.addObject("productList", productService.findByCategory(lCategoryId));
        mv.addObject("categoryList", categoryService.listCategory());
        return mv;
    }

    @GetMapping("error")
    public String error() {
        return "error";
    }
}
