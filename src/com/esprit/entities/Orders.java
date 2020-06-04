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
public class Orders {
    
    private int id;
    private String nom;
    private String email;
    private String phonenumber;
    private String adresse;
    private String city;
    private String instructions_livraisons;
    private int total;

    public Orders() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Orders(String nom, String email, String phonenumber, String adresse, String city, int total) {
        this.nom = nom;
        this.email = email;
        this.phonenumber = phonenumber;
        this.adresse = adresse;
        this.city = city;
       // this.instructions_livraisons = instructions_livraisons;
        this.total = total;
    }

    public Orders(int id, String nom, String email, String phonenumber, String adresse, String city, String instructions_livraisons, int total) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.phonenumber = phonenumber;
        this.adresse = adresse;
        this.city = city;
        this.instructions_livraisons = instructions_livraisons;
        this.total = total;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInstructions_livraisons() {
        return instructions_livraisons;
    }

    public void setInstructions_livraisons(String instructions_livraisons) {
        this.instructions_livraisons = instructions_livraisons;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
    
}
