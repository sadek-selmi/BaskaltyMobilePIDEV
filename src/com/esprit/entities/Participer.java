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
public class Participer {
    private String champs;
    private int Confirmation ;
    private String cshamps;
    private int id ;
    private int id_event ;
    private int id_user ;
    private Boolean k ;

    public Boolean getK() {
        return k;
    }

    public void setK(Boolean k) {
        this.k = k;
    }

    public Participer(int id, int id_event, int id_user) {
        this.id = id;
        this.id_event = id_event;
        this.id_user = id_user;
    }
    public Participer( int id_user, int id_event) {
        this.id_event = id_event;
        this.id_user = id_user;
    }

    public Participer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    

    public String getCshamps() {
        return cshamps;
    }

    public Participer(String champs, String cshamps) {
        this.champs = champs;
        this.cshamps = cshamps;
    }

    public void setCshamps(String cshamps) {
        this.cshamps = cshamps;
    }

    public Participer(String champs) {
        this.champs = champs;
    }

    public Participer(int Confirmation) {
        this.Confirmation = Confirmation;
    }

    public Participer(String champs, int Confirmation) {
        this.champs = champs;
        this.Confirmation = Confirmation;
    }

    @Override
    public String toString() {
        return "Participer{" + "champs=" + champs + ", Confirmation=" + Confirmation + ", cshamps=" + cshamps + ", id=" + id + ", id_event=" + id_event + ", id_user=" + id_user + ", k=" + k + '}';
    }

  

    

    public String getChamps() {
        return champs;
    }

    public void setChamps(String champs) {
        this.champs = champs;
    }

    public int getConfirmation() {
        return Confirmation;
    }

    public void setConfirmation(int Confirmation) {
        this.Confirmation = Confirmation;
    }
    
    
}
