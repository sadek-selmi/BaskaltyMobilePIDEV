/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author selmi
 */
public class Publication {
    private int Id;
    private int Id_User;
    private int accept;   
            private String datecreation;
    private String image;
    private String contenu;

    public Publication() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getId_User() {
        return Id_User;
    }

    public void setId_User(int Id_User) {
        this.Id_User = Id_User;
    }

    public int getAccept() {
        return accept;
    }

    public void setAccept(int accept) {
        this.accept = accept;
    }

    public String getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(String datecreation) {
        this.datecreation = datecreation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Publication(int Id, int Id_User, int accept, String datecreation, String image, String contenu) {
        this.Id = Id;
        this.Id_User = Id_User;
        this.accept = accept;
        this.datecreation = datecreation;
        this.image = image;
        this.contenu = contenu;
    }

    public Publication(int Id_User, int accept, String datecreation, String image, String contenu) {
        this.Id_User = Id_User;
        this.accept = accept;
        this.datecreation = datecreation;
        this.image = image;
        this.contenu = contenu;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "Publication{" + "Id=" + Id + ", Id_User=" + Id_User + ", accept=" + accept + ", datecreation=" + datecreation + ", image=" + image + ", contenu=" + contenu + '}';
    }

    public Publication(int Id, int Id_User, String image, String contenu) {
        this.Id = Id;
        this.Id_User = Id_User;
        this.image = image;
        this.contenu = contenu;
    }

    public Publication(int Id_User, String image, String contenu) {
        this.Id_User = Id_User;
        this.image = image;
        this.contenu = contenu;
    }

    public Publication(String image, String contenu) {
        this.image = image;
        this.contenu = contenu;
    }

  
    
    
    
    
    
}
