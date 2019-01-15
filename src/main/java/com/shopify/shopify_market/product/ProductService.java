package com.shopify.shopify_market.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * @param notSoldOut boolean, true: return only non-zero inventory product
     *                            false: return all product
     * @return a list of product depending on passed in parameter.
     */
    public  List<Product> getAllProduct(boolean notSoldOut){
        List<Product> productArrayList = new ArrayList<>();
        if(!notSoldOut){
            productRepository.findAll().forEach(productArrayList::add);
            return productArrayList;
        }else{
            return productRepository.nonEmptyInventory();
        }
    }

    public void addProduct (Product product){
        productRepository.save(product);
    }

    /**
     * @param id Id of product
     * @return the product if it exists, null if does not
     */
    public Product getProduct (long id){
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(long id){
        productRepository.deleteById(id);
    }

    /**
     * @param id ID of product to be purchased
     *           automatically remove 1 inventory from the product
     */
    public void purchaseProduct(long id){
        Product product = getProduct(id);
        int inventory = product.getInventory();
        if(inventory > 1) {
            product.setInventory(inventory - 1);
            productRepository.save(product);
        }
    }

}
