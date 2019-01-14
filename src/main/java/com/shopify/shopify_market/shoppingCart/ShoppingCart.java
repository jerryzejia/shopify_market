package com.shopify.shopify_market.shoppingCart;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shopify.shopify_market.product.Product;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "shoppingcart", schema = "productrep")
public class ShoppingCart {
    @Id private long id;
    private double currentprice;
    private String cartitemjson;

    @Transient private Map<Long, Integer> cartItems= new HashMap<>();

    public ShoppingCart(){

    }

    public ShoppingCart(long id){
        this.id = id;
        this.currentprice = 0;
        this.cartitemjson = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCurrentprice(){
        return currentprice;
    }

    public String getCartitemjson(){
        return cartitemjson;
    }

    @Transient
    public void getCartItems() {
        if (cartitemjson != null) {
            cartItems = new Gson().fromJson(cartitemjson, new TypeToken<HashMap<Long, Integer>>() {
            }.getType());
        } else {
            cartItems = new HashMap<>();
        }
    }

    @Transient
    public void convertToJson(){
        cartitemjson = new Gson().toJson(cartItems);
    }

    public void clear(){
        getCartItems();
        this.cartItems.clear();
    }

    public void removeProduct(Product product, int remove_inventory){
        getCartItems();
        if(cartItems.containsKey(product.getId())){
            if(cartItems.get(product.getId()) - remove_inventory > 0){
                cartItems.put(product.getId(), cartItems.get(product.getId())-remove_inventory);
                currentprice -= remove_inventory * product.getPrice();
            }
        }
        convertToJson();
    }

    @Transient
    public void addItem(Product product) {
        getCartItems();
        if (cartItems.containsKey(product.getId()) && cartItems != null) {
            cartItems.put(product.getId(), cartItems.get(product.getId())+1);
        } else {
            cartItems.put(product.getId(), 1);
        }
        currentprice += product.getPrice();
        convertToJson();
    }

}
