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
public class comment {
       private int id;
   
   private String content;
    private int idevent;
      private String date_question ;
      	private String username ;
        private String imageFile ;
         private int iduser;

    public comment(String content, int idevent, int iduser) {
        this.content = content;
        this.idevent = idevent;
        this.iduser = iduser;
    }

    public comment(String content, int idevent) {
        this.content = content;
        this.idevent = idevent;
   
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageFile() {
        return imageFile;
    }

    public void setImageFile(String imageFile) {
        this.imageFile = imageFile;
    }
    

    public comment(int id, String content, int idevent) {
        this.id = id;
        this.content = content;
        this.idevent = idevent;
    
    }

    public comment(int id ,String content, int idevent, String username, String imageFile) {
                this.id = id;

        this.content = content;
        this.idevent = idevent;
        this.username = username;
        this.imageFile = imageFile;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

    public String getDate_question() {
        return date_question;
    }

    public void setDate_question(String date_question) {
        this.date_question = date_question;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Override
    public String toString() {
        return "comment{" + "id=" + id + ", content=" + content + ", idevent=" + idevent + ", date_question=" + date_question + ", username=" + username + ", imageFile=" + imageFile + ", iduser=" + iduser + '}';
    }

    
}
