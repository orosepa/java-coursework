package com.coursework.ecomarket.controllers;

import com.coursework.ecomarket.models.Product;
import com.coursework.ecomarket.models.User;
import com.coursework.ecomarket.services.ProductService;
import com.coursework.ecomarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("account")
public class AccountController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("cart")
    public ModelAndView cart(Principal principal) {
        ModelAndView mv = new ModelAndView("account/cart");
        User user = userService.findByEmail(principal.getName());
        mv.addObject("user", user);
        int totalSum = getSum(user);
        mv.addObject("total", totalSum);

        return mv;
    }

    @GetMapping("add/{productId}")
    public ModelAndView add(@PathVariable("productId") String productId, Principal principal) {
        ModelAndView mv = new ModelAndView("account/cart");
        User user = userService.findByEmail(principal.getName());
        long lProductId = Long.parseLong(productId);
        Product product = productService.getProductById(lProductId).get();

        List<Product> products = new ArrayList<>();
        products.add(product);
        user.setProductList(products);

        List<User> users = new ArrayList<>();
        users.add(user);
        product.setUserList(users);

        userService.update(user);
        productService.addProduct(product);
        int totalSum = getSum(user);
        mv.addObject("total", totalSum);
        mv.addObject("user", user);

        return mv;
    }

    private int getSum(User user) {
        List<Product> products = user.getProductList();
        int sum = 0;

        for (Product product : products)
            sum += product.getProductPrice();

        return sum;
    }

}
