package com.shopify.shopify_market.shoppingCart;

import com.shopify.shopify_market.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public void addShoppingCart(ShoppingCart shoppingCart){
        if(getShoppingCart(shoppingCart.getId()) == null) {
            shoppingCartRepository.save(shoppingCart);
        }
    }

    /**
     * @param id Shopping cart ID
     * @return ShoppingCart if it exists, Null if not.
     */
    public ShoppingCart getShoppingCart(long id){
        return shoppingCartRepository.findById(id).orElse(null);
    }

    /**
     * @param shoppingCartId the shoppingcart referenced to
     * @param product product to be removed from the shopping cart
     */
    public void deleteItemFromShopingCart(long shoppingCartId, Product product){
        if(getShoppingCart(shoppingCartId)!= null) {
            ShoppingCart sc = getShoppingCart(shoppingCartId);
            sc.removeProduct(product);
            shoppingCartRepository.save(sc);
        }
    }

    /**
     * @param shoppingCartId the shoppingcart referenced to
     * @param product product to be added to the shopping cart, need to have a positive inventory
     */
    //Only allows buying one at a time,
    public void buyItemToShopingCart(long shoppingCartId, Product product){
        if(getShoppingCart(shoppingCartId)!= null && product.getInventory() > 0) {
            ShoppingCart sc = getShoppingCart(shoppingCartId);
            sc.addItem(product);
            shoppingCartRepository.save(sc);
        }
    }

    /**
     * @param shoppingCartId the ID of the shopping cart that is about to be deleted
     */
    public void deteleShoppingCart(long shoppingCartId){
        if(getShoppingCart(shoppingCartId) != null){
            shoppingCartRepository.deleteById(shoppingCartId);
        }
    }


}
