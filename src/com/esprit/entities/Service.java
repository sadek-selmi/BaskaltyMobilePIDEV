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
public class Service {
    private int id ;
    private String nom_service;

    public Service() {
    }

    public Service(int id, String nom_service) {
        this.id = id;
        this.nom_service = nom_service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_service() {
        return nom_service;
    }

    public void setNom_service(String nom_service) {
        this.nom_service = nom_service;
    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", nom_service=" + nom_service + '}';
    }
    
}
