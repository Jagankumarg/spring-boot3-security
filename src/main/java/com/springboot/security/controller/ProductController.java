package com.springboot.security.controller;

import com.springboot.security.dto.Product;
import com.springboot.security.entity.UserInfo;
import com.springboot.security.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/details")
    public String welcome(){
        return "end point is not secure";
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody UserInfo userInfo){

        return productService.addUser(userInfo);
    }
    @GetMapping("/test/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Product> getAllTheProducts(){
        return productService.getProducts();

    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProductById(@PathVariable int id){
        return productService.getProduct(id);
    }
}
