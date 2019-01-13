package com.shopify.shopify_market.product;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product", schema = "productrep")
public class Product {

    @Id
    private long id;
    private String name;
    private int inventory;
    private double price;

    public Product(){

    }

    public Product(long id, String name, double price, int inventory) {
        this.id = id;
        this.name = name;
        this.inventory = inventory;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getInventory() {
        return inventory;
    }

    public double getPrice() {
        return price;
    }
}
