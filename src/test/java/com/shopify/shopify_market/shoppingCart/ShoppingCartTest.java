package com.shopify.shopify_market.shoppingCart;

import com.shopify.shopify_market.product.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.Assert;


import java.util.HashMap;
import java.util.Map;

public class ShoppingCartTest {

    @Parameterized.Parameters
    public static Map<Long, Integer> data(){
        HashMap<Long, Integer> cartItems = new HashMap<>();
        return cartItems;
    }

    @Before
    public void setUp() throws Exception {
        String cartItemsJson = "";
    }

    @Test
    public void convertCartItems() {
        ShoppingCart tester = new ShoppingCart();
        Product product = new Product(1, "Java", 20, 20);
        Map<Long, Integer> cartItemsTest = new HashMap<>();
        cartItemsTest.put((long) 1, 1);
        tester.addItem(product);
        tester.convertCartItems();
        Assert.assertEquals(cartItemsTest, tester.getCartItems());
    }

    @Test
    public void convertToJson() {
        ShoppingCart tester = new ShoppingCart();
        Product product = new Product(1, "Java", 20, 20);
        tester.convertToJson();
        Assert.assertEquals("{}", tester.getCartitemjson());
        tester.addItem(product);
        Assert.assertEquals("{\"1\":1}",tester.getCartitemjson());
    }

}