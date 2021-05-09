package com.coursework.ecomarket.controllers;

import com.coursework.ecomarket.models.Category;
import com.coursework.ecomarket.models.Product;
import com.coursework.ecomarket.services.CategoryService;
import com.coursework.ecomarket.services.FileUploadService;
import com.coursework.ecomarket.services.ProductService;
import com.coursework.ecomarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private FileUploadService fileUploadService;


    @GetMapping("index")
    public String index() {
        return "admin/index";
    }

    //	For Users--------------------------------------------------

    @GetMapping("user-list")
    public String users(Model model) {
        model.addAttribute("userList", userService.findAllUser());
        return "admin/user-list";
    }

    @GetMapping("delete-user/{userId}")
    public ModelAndView deleteUser(@PathVariable("userId") String userId) {
        ModelAndView mv = new ModelAndView("admin/user-list");
        userService.deleteUser(Long.parseLong(userId));
        mv.addObject("userList", userService.findAllUser());
        return mv;
    }

    @GetMapping("add-user")
    public String addUser() {
        return "admin/add-user";
    }

    //	For Category--------------------------------------------------
    @GetMapping("category-form")
    public ModelAndView listCategory() {
        ModelAndView mv = new ModelAndView("admin/category-form");
        mv.addObject("categoryList", categoryService.listCategory());
        return mv;
    }

    @PostMapping("add-category")
    public ModelAndView addCategory(Category category) {
        ModelAndView mv = new ModelAndView("admin/category-form");
        categoryService.addCategory(category);
        mv.addObject("categoryList", categoryService.listCategory());
        return mv;
    }

    @GetMapping("delete-Category/{categoryId}")
    public ModelAndView deleteCategory(@PathVariable("categoryId")String categoryId) {
        ModelAndView mv = new ModelAndView("admin/category-form");
        categoryService.deleteCategory(Long.parseLong(categoryId));
        mv.addObject("categoryList", categoryService.listCategory());
        return mv;
    }

    @GetMapping("updateCategory/{categoryId}")
    public ModelAndView updateCategory(@PathVariable("categoryId")String categoryId) {
        ModelAndView mv = new ModelAndView("admin/update-category");
        mv.addObject("Category", categoryService.getCategory(Long.parseLong(categoryId)).get());
        return mv;
    }

    //	For Product--------------------------------------------------
    @GetMapping("product-form")
    public ModelAndView listProduct() {
        ModelAndView mv = new ModelAndView("admin/product-form");
        mv.addObject("categoryList", categoryService.listCategory());
        mv.addObject("productList", productService.listProduct());
        return mv;
    }

    @PostMapping("add-product")
    public ModelAndView addProduct(Product product, @RequestParam("file") MultipartFile file) {
        ModelAndView mv = new ModelAndView("admin/product-form");
        System.out.println("file: " + file.getOriginalFilename());
        String filePath = fileUploadService.upload(file);
        product.setImage(filePath);

        System.out.println(product.getImage());

        productService.addProduct(product);
        mv.addObject("productList", productService.listProduct());
        return mv;
    }

    @GetMapping("delete-product/{productId}")
    public ModelAndView deleteProduct(@PathVariable("productId")String productId) {
        ModelAndView mv = new ModelAndView("admin/product-form");
        productService.deleteProduct(Long.parseLong(productId));
        mv.addObject("productList", productService.listProduct());
        return mv;
    }

    @GetMapping("update-product/{productId}")
    public ModelAndView updateProduct(@PathVariable("productId")String productId) {
        ModelAndView mv = new ModelAndView("admin/update-product");
        mv.addObject("categoryList", categoryService.listCategory());
        mv.addObject("Product", productService.getProductById(Long.parseLong(productId)).get());
        return mv;
    }


}
