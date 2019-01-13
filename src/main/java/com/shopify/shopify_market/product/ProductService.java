package com.shopify.shopify_market.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

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

    public Product getProduct (long id){
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(long id){
        productRepository.deleteById(id);
    }

    public void purchaseProduct(long id){
        Product product = getProduct(id);
        int inventory = product.getInventory();
        if(inventory > 1) {
            product.setInventory(inventory - 1);
            productRepository.save(product);
        }
    }

}
