package com.shopify.shopify_market.shoppingCart;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.shopify.shopify_market.product.Product;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Entity
public class ShopingCart {
    @Id
    private long id;
    private double current_price;
    String cartItemsJson;
    private Map<Product, Integer> cartItems= new HashMap<>();

    public ShopingCart(Long id){
        this.id = id;
        this.current_price = 0;
    }

    public Map<Product, Integer> getCartItems() {
        return cartItems;
    }

    public void clear(){
        this.cartItems.clear();
    }

    public void addItem(Product product) {
        if (cartItems.containsKey(product)) {
            cartItems.put(product, cartItems.get(product)+1);
        } else {
            cartItems.put(product, 1);
        }
        convertToJson();
    }

    public void convertToJson(){
        Gson gson = new Gson();
        cartItemsJson = gson.toJson(cartItems);
    }
}
