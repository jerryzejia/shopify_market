package com.shopify.shopify_market.shoppingCart;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shopify.shopify_market.product.Product;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "shoppingcart", schema = "productrep")
public class ShoppingCart {
    @Id
    private long id;
    private double currentprice;
    private String cartitemjson;
    /**
     * Key: ID of the product
     * Value: Number of product inside the shopping cart
     * Not stored inside database.
     */
    @Transient
    private Map<Long, Integer> cartItems = new HashMap<>();

    public ShoppingCart() {

    }
    /**
     * @param id: id of the shoppingCart
     *        currentprice: the current total price of the items inside the shopping cart. 0 at initialization.
     *        cartitemjson: a json string that stores the product inside the shopping cart. Null at initialization.
     */
    public ShoppingCart(long id) {
        this.id = id;
        this.currentprice = 0;
        this.cartitemjson = "";
    }

    public long getId() {
        return id;
    }

    public double getCurrentprice() {
        return currentprice;
    }

    public String getCartitemjson() {
        return cartitemjson;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Transient
    public Map<Long, Integer> getCartItems(){
        return cartItems;
    }

    @Transient
    public void convertCartItems() {
        if (cartitemjson != null) {
            cartItems = new Gson().fromJson(cartitemjson, new TypeToken<HashMap<Long, Integer>>() {
            }.getType());
        } else {
            cartItems = new HashMap<>();
        }
    }

    @Transient
    public void convertToJson() {
        cartitemjson = new Gson().toJson(cartItems);
    }

    /**
     * Clear all items in shopping cart
     */
    public void clear() {
        convertCartItems();
        this.cartItems.clear();
        convertToJson();
    }

    public void removeProduct(Product product) {
        convertCartItems();
        if (cartItems.containsKey(product.getId())) {
            if (cartItems.get(product.getId()) - 1 > 0) {
                cartItems.put(product.getId(), cartItems.get(product.getId()) - 1);
                currentprice -=  product.getPrice();
            }
        }
        convertToJson();
    }

    /**
     * @param product product to be added
     *                converts JSON file into a hashmap and make changes to hashmap.
     *                converts back to JSON file to store it inside a database for persistence.
     */
    @Transient
    public void addItem(Product product) {
        convertCartItems();
        if (cartItems.containsKey(product.getId()) && cartItems != null) {
            cartItems.put(product.getId(), cartItems.get(product.getId()) + 1);
        } else {
            cartItems.put(product.getId(), 1);
        }
        currentprice += product.getPrice();
        convertToJson();
    }

}
