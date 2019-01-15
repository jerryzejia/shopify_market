package com.shopify.shopify_market.product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    /**
     * @return list of non-zero inventory products
     *         empty list if everything is sold out.
     */
    @Query("SELECT r FROM Product r where r.inventory > 0")
    List<Product> nonEmptyInventory();

}
