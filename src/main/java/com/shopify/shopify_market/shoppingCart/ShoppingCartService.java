package com.shopify.shopify_market.shoppingCart;

import com.shopify.shopify_market.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public void addShoppingCart(ShoppingCart shoppingCart){
        shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart getShoppingCart(long id){
        return shoppingCartRepository.findById(id).orElse(null);
    }

    public void deleteItemFromShopingCart(long shoppingCartId, Product product, int remove_inventory){
        ShoppingCart sc = getShoppingCart(shoppingCartId);
        sc.removeProduct(product, remove_inventory);
        shoppingCartRepository.save(sc);
    }

    //Only allows buying one at a time,
    public void buyItemToShopingCart(long shoppingCartId, Product product){
        if(getShoppingCart(shoppingCartId)!= null) {
            ShoppingCart sc = getShoppingCart(shoppingCartId);
            sc.addItem(product);
            shoppingCartRepository.save(sc);
        }
    }
}
