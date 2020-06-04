/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;



/**
 *
 * @author benha
 */
public class Product {
    
    private int id;
    private int quantity;
    private String name;
    private String reference;
    private String description;
    private int price;
    private String image;
    private int id_user;

    public Product() {
    }

    public Product(int id, int quantity, String name, String reference, String description, int price) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.reference = reference;
        this.description = description;
        this.price = price;
    }
    
    
    

    public Product(int id, int quantity, String name, String reference, String description, int price, String image) {
        this.id = id;
        this.quantity = quantity;
        this.name = name;
        this.reference = reference;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public Product(int quantity, String name, String reference, String description, int price, String image) {
        this.quantity = quantity;
        this.name = name;
        this.reference = reference;
        this.description = description;
        this.price = price;
        this.image = image;
    }
   
    
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

  
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", quantity=" + quantity + ", name=" + name + ", reference=" + reference + ", description=" + description + ", price=" + price + ", image=" + image + ", id_user=" + id_user + '}';
    }

   
    
}
