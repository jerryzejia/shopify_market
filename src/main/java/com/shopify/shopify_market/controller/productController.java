package com.shopify.shopify_market.controller;

import com.shopify.shopify_market.product.Product;
import com.shopify.shopify_market.product.ProductService;
import com.shopify.shopify_market.shoppingCart.ShoppingCart;
import com.shopify.shopify_market.shoppingCart.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class productController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping("/hello")
    public String getHelloMessage() {
        return "Hello Spring Boot world!";
    }

    @PostMapping( "/product")
    public void addProduct(@RequestBody Product product){
        productService.addProduct(product);
    }

    @GetMapping("/product")
    public List<Product> getAllProduct(
            @RequestParam(value = "hasInventory", required = false) boolean inventory){
            return productService.getAllProduct(inventory);
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable(name = "id") long id){
        return productService.getProduct(id);
    }

    @PostMapping("/purchase/{shoppingCartId}/{id}")
    public void purchaseProduct(@PathVariable(name = "id") long id,
                                @PathVariable(name = "shoppingCartId") long shopingCartId){
        productService.purchaseProduct(id);
        shoppingCartService.buyItemToShopingCart(shopingCartId, productService.getProduct(id));
    }

    @PostMapping("/ShoppingCart")
    public void createNewShoppingCart(@RequestBody ShoppingCart shoppingCart){
        shoppingCartService.addShoppingCart(shoppingCart);
    }

    @GetMapping("/ShoppingCart/{id}")
    public ShoppingCart getShoppingCart(@PathVariable(name = "id") long id){
       return shoppingCartService.getShoppingCart(id);
    }



}
