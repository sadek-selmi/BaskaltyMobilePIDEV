/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import com.codename1.ui.spinner.Picker;
import java.util.Date;


/**
 *
 * @author 
 */
public class events {
    
     private int Id_Event;
    private int Id_User;
    private String Titre;
    private String Contenu;
    private int nbre_vues;
    private int Participernumber;
    private int Interstednumber;
    private int commenternumber;
    private String location;
private String end;
    private String start;
    private String image;

    private Picker debut;
    private Picker fin;
    private int prix;
    private int quantity;
private theme theme ;
    public events() {
    }

    


   

    public int getId_Event() {
        return Id_Event;
    }

    public void setId_Event(int Id_Event) {
        this.Id_Event = Id_Event;
    }

    public int getId_User() {
        return Id_User;
    }

    public void setId_User(int Id_User) {
        this.Id_User = Id_User;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String Titre) {
        this.Titre = Titre;
    }

    public Picker getDebut() {
        return debut;
    }

    public void setDebut(Picker debut) {
        this.debut = debut;
    }

    public Picker getFin() {
        return fin;
    }

    public void setFin(Picker fin) {
        this.fin = fin;
    }

    public theme getTheme() {
        return theme;
    }

    public void setTheme(theme theme) {
        this.theme = theme;
    }

    public String getContenu() {
        return Contenu;
    }

    public void setContenu(String Contenu) {
        this.Contenu = Contenu;
    }

    public int getNbre_vues() {
        return nbre_vues;
    }

    public void setNbre_vues(int nbre_vues) {
        this.nbre_vues = nbre_vues;
    }

    public int getParticipernumber() {
        return Participernumber;
    }

    public void setParticipernumber(int Participernumber) {
        this.Participernumber = Participernumber;
    }

    public int getInterstednumber() {
        return Interstednumber;
    }

    public void setInterstednumber(int Interstednumber) {
        this.Interstednumber = Interstednumber;
    }

    public int getCommenternumber() {
        return commenternumber;
    }

    public void setCommenternumber(int commenternumber) {
        this.commenternumber = commenternumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "events{" + "Id_Event=" + Id_Event + ", Id_User=" + Id_User + ", Titre=" + Titre + ", Contenu=" + Contenu + ", nbre_vues=" + nbre_vues + ", Participernumber=" + Participernumber + ", Interstednumber=" + Interstednumber + ", commenternumber=" + commenternumber + ", location=" + location + ", end=" + end + ", start=" + start + ", image=" + image + ", debut=" + debut + ", fin=" + fin + ", prix=" + prix + ", quantity=" + quantity + ", theme=" + theme + '}';
    }

  

    



 
  public events(int Id_User, String Titre, String Contenu, String location,  String start, String image,String end, int prix, int quantity,int Interstednumber,int Participernumber) {
        this.Id_User = Id_User;
        this.Titre = Titre;
        this.Contenu = Contenu;
        this.location = location;
        this.end = end;
        this.start = start;
        this.image = image;
        this.prix = prix;
        this.quantity = quantity;
        this.Participernumber=Participernumber;
        this.Interstednumber= Interstednumber;
    }

 
  public events(int Id_User, String Titre, String Contenu, String location,  String start, String image,String end, int prix, int quantity) {
        this.Id_User = Id_User;
        this.Titre = Titre;
        this.Contenu = Contenu;
        this.location = location;
        this.end = end;
        this.start = start;
        this.image = image;
        this.prix = prix;
        this.quantity = quantity;
        
    }
   
  public events(int Id_User, String Titre, String Contenu, String location,  String start,String end, int prix, int quantity) {
        this.Id_User = Id_User;
        this.Titre = Titre;
        this.Contenu = Contenu;
        this.location = location;
        this.end = end;
        this.start = start;
        this.prix = prix;
        this.quantity = quantity;
    }

    
    

}
