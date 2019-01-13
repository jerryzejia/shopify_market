package com.shopify.shopify_market.controller;

import com.shopify.shopify_market.product.Product;
import com.shopify.shopify_market.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class productController {

    @Autowired
    private ProductService productService;

    @GetMapping("/hello")
    public String getHelloMessage() {
        return "Hello Spring Boot world!";
    }

    @PostMapping( "/product")
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }
/*
    @GetMapping("/product/{hasInventory}")
    public List<Product> getAllProduct(@PathVariable(name = "hasInventory", required = false) String hasInventory){
        boolean notSoldOut = hasInventory.equals("hasInventory");
        return productService.getAllProduct(notSoldOut);
    }
*/
    @GetMapping("/product")
    public List<Product> getAllProduct(
            @RequestParam(value = "hasInventory", required = false) boolean inventory){
            return productService.getAllProduct(inventory);
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable(name = "id") long id){
        return productService.getProduct(id);
    }

    @PostMapping("/purchase/{id}")
    public void purchaseProduct(@PathVariable(name = "id") long id){
        productService.purchaseProduct(id);
    }
}
