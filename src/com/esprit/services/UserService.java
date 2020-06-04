/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.NewsfeedForm;
import com.codename1.uikit.cleanmodern.ProfileForm;
import com.codename1.uikit.cleanmodern.SignInForm;
import com.esprit.entities.User;
import com.esprit.utils.DataSource;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author houss
 */
public class UserService {

    private ConnectionRequest request;
    private boolean responseResult;
    //User parseuser = new Us
  //  User u = new User();

    ArrayList<User> listeusers = new ArrayList<>();

    public UserService() {
        request = DataSource.getInstance().getRequest();
    }
    
    
    public ArrayList<User> findUserByid( int id )
    {
          String url = Statics.BASE_URL + "/Profile/find?id="+id;
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

              listeusers = parseUser(new String(request.getResponseData()));
             
               request.removeResponseListener(this);
            }
            
        });
         NetworkManager.getInstance().addToQueueAndWait(request);
        return listeusers;
    }
    
    
    public ArrayList<User> parseUser(String json)
    {
       
           try {
            JSONParser j = new JSONParser();
            Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) user.get("root");
            for (Map<String, Object> obj : list) {

              int id = (int) Float.parseFloat(obj.get("id").toString());
                
              String email = obj.get("email").toString();

               String  password = obj.get("password").toString();
                
              String username = obj.get("username").toString();
           
              listeusers.add(new User(id, username, password, email));
      
            }

        } catch (IOException ex) {
            Dialog.show("error",ex.getMessage(),"ok",null);
        }
  
      return listeusers;
    }
    
    
    
    public void editProfile(int id,String username,String email,String password,String confirmpassword)
    {
        String url = Statics.BASE_URL +"/Profile/edit/"+id+"/"+username+"/"+email+"/"+password+"/"+confirmpassword ; 
         request.setUrl(url);
         request.setPost(true);
          request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) 
             {
               
             if ( responseResult= request.getResponseCode() == 200 )
             {
                  ToastBar.showMessage("Account Updated Succcessfully !!", FontImage.MATERIAL_CHECK);
                  request.removeResponseListener(this);
             }
             else
             {
                  ToastBar.showMessage("An error has been occured, try again !", FontImage.MATERIAL_ERROR);
             }
                 
             }
         });
         NetworkManager.getInstance().addToQueue(request);
         
    }
    
    
    
    public void Register (String username,String email,String password,String repeatedPassword,String role,Resources theme,String image)
    {
         String url = Statics.BASE_URL + "/Register/"+username+"/"+email+"/"+password+"/"+repeatedPassword+"/"+role+"/"+image;
         request.setUrl(url);
         request.setPost(true);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) 
             {
               
             if ( responseResult= request.getResponseCode() == 200 )
             {
                  ToastBar.showMessage("Account created Succcessfully !!", FontImage.MATERIAL_CHECK);
                  new SignInForm(theme).show();
                  request.removeResponseListener(this);
             }
             else
             {
                  ToastBar.showMessage("An error has been occured, try again !", FontImage.MATERIAL_ERROR);
             }
                 
             }
         });
         NetworkManager.getInstance().addToQueue(request);
     
    }

    public ArrayList<User> login(String email, String password) 
    {

        String url = Statics.BASE_URL + "/login/"+email+"/"+password;
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                listeusers = getUser(new String(request.getResponseData()));
             
               request.removeResponseListener(this);
            }
            
        });
         NetworkManager.getInstance().addToQueueAndWait(request);
         return listeusers;
    }

    public ArrayList<User> getUser(String json) {

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) user.get("root");
            for (Map<String, Object> obj : list) {

               int id = (int) Float.parseFloat(obj.get("id").toString());
                
                String email = obj.get("email").toString();

                String password = obj.get("password").toString();
                
                String username = obj.get("username").toString();
                
               String enabled = obj.get("mobile").toString();
                
               String image = obj.get("imageFile").toString();
               
                String roles = obj.get("roles").toString();
                
              
                listeusers.add(new User(id,username, password, email, roles,enabled,image));
            }

        } catch (IOException ex) {
            Dialog.show("error",ex.getMessage(),"ok",null);
        }
  
        return listeusers;

    }

}
