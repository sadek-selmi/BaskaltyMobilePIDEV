/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author yaya
 */
public class mecanicien {
    
   
     private int id;
    private Service service;
    private String nom;
    private String prenom;
    private String description;
    private String mail;
    private String image;
    private float prix;
    private int num_tel;
    private String experience;
    private String region;
    private String city;

    public mecanicien( String nom, String prenom, String description, String mail, String image, float prix, int num_tel, String experience, String region, String city, int adomicile) {
        this.nom = nom;
        this.prenom = prenom;
        this.description = description;
        this.mail = mail;
        this.image = image;
        this.prix = prix;
        this.num_tel = num_tel;
        this.experience = experience;
        this.region = region;
        this.city = city;
        this.adomicile = adomicile;
    }

    public mecanicien(int id, String nom, String prenom, String description, String mail, String image, float prix, String experience) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.description = description;
        this.mail = mail;
        this.image = image;
        this.prix = prix;
        this.experience = experience;
    }

    public mecanicien(String nom, String prenom, String description, String mail, String image, float prix, String experience) {
        this.nom = nom;
        this.prenom = prenom;
        this.description = description;
        this.mail = mail;
        this.image = image;
        this.prix = prix;
        this.experience = experience;
    }

    public mecanicien(int id, String nom, String prenom, String description, String mail, String image, float prix, String experience, String region, String city) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.description = description;
        this.mail = mail;
        this.image = image;
        this.prix = prix;
        this.experience = experience;
        this.region = region;
        this.city = city;
    }

    public mecanicien() {
    }

    @Override
    public String toString() {
        return "mecanicien{" + "id=" + id + ", service=" + service + ", nom=" + nom + ", prenom=" + prenom + ", description=" + description + ", mail=" + mail + ", image=" + image + ", prix=" + prix + ", num_tel=" + num_tel + ", experience=" + experience + ", region=" + region + ", city=" + city + ", adomicile=" + adomicile + '}';
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAdomicile() {
        return adomicile;
    }

    public void setAdomicile(int adomicile) {
        this.adomicile = adomicile;
    }
    private int adomicile;
    
}