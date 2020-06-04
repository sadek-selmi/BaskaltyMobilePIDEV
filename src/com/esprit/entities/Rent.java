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
public class Rent {
    
    private int id;
    private int quantity;
    private String marque;
    private String reference;
    private String description;
        private String model;
    private String localisation;
    private int days;

    private int prix;
    private String image;
    private int id_user;

    public Rent() {
    }

    public Rent(int id, int quantity, String marque, String reference, String description, String model, String localisation, int days, int prix, String image, int id_user) {
        this.id = id;
        this.quantity = quantity;
        this.marque = marque;
        this.reference = reference;
        this.description = description;
        this.model = model;
        this.localisation = localisation;
        this.days = days;
        this.prix = prix;
        this.image = image;
        this.id_user = id_user;
    }
   public Rent(int id, int quantity, String marque, String reference, String description, String model, String localisation, int days, int prix, String image) {
        this.id = id;
        this.quantity = quantity;
        this.marque = marque;
        this.reference = reference;
        this.description = description;
        this.model = model;
        this.localisation = localisation;
        this.days = days;
        this.prix = prix;
        this.image = image;
    }
    public Rent(int quantity, String marque, String reference, String description, String model, String localisation, int days, int prix, String image, int id_user) {
        this.quantity = quantity;
        this.marque = marque;
        this.reference = reference;
        this.description = description;
        this.model = model;
        this.localisation = localisation;
        this.days = days;
        this.prix = prix;
        this.image = image;
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Rent{" + "id=" + id + ", quantity=" + quantity + ", marque=" + marque + ", reference=" + reference + ", description=" + description + ", model=" + model + ", localisation=" + localisation + ", days=" + days + ", prix=" + prix + ", image=" + image + ", id_user=" + id_user + '}';
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

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
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

    
    
}
