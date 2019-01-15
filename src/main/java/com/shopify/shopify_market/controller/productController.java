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

    /**
     * @param product A product that is about to be added inside the database,
     *                represented by a JSON in request body.
     */
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

    /**
     * @param shoppingCart A shopping cart that is about to be added inside the database,
     *                     represented by a JSON in request body.
     */
    @PostMapping("/ShoppingCart")
    public void addShoppingCart(@RequestBody ShoppingCart shoppingCart){
        shoppingCartService.addShoppingCart(shoppingCart);
    }

    @GetMapping("/ShoppingCart/{id}")
    public ShoppingCart getShoppingCart(@PathVariable(name = "id") long id){
       return shoppingCartService.getShoppingCart(id);
    }

    @DeleteMapping("/ShoppingCart/{id}")
    public void deleteShoppingCart(@PathVariable(name = "id") long id){
        shoppingCartService.deteleShoppingCart(id);
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable(name = "id") long id){
        productService.deleteProduct(id);
    }



}
