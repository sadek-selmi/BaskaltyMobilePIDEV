/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.Panier;
import com.esprit.entities.Product;
import com.esprit.utils.DataSource;
import com.esprit.utils.Statics;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author benha
 */
public class PanierService {
    
      private ConnectionRequest request;

    private boolean responseResult;
    ArrayList<Panier> panier = new ArrayList<>();

    public PanierService() {
         request = DataSource.getInstance().getRequest();
    }
    
       public boolean AddToPanier (int id_pro,int quantity) {
        String url = Statics.BASE_URL + "/panier/add/"+id_pro+"?quantity="+quantity;

        request.setUrl(url);
        request.setPost(true);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }
    
         public ArrayList<Panier> parsePanier (String jsonText) {
        try {
        
            JSONParser jp = new JSONParser(); 
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                
               int id = (int)Float.parseFloat(obj.get("id").toString());
               int quantity = (int) Float.parseFloat(obj.get("quantity").toString());
               int price = (int) Float.parseFloat(obj.get("prix").toString());
               String name = obj.get("name").toString();
               String image = obj.get("image").toString();
              
               panier.add(new Panier(id, quantity, price, name, image));
                //panier.add(new Panier(id, quantity, price));
            }

        } catch (IOException ex) {
        }

        return panier;
    }
       
       public ArrayList<Panier> getPanier() {
        String url = Statics.BASE_URL + "/panier";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                panier = parsePanier(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return panier;
    }
       
       
      public void DeleteFromPanier (int id_panier) {

        String url = Statics.BASE_URL + "/panier/delete?id="+id_panier;
        request.setUrl(url);
        request.setPost(true);
        
        request.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (responseResult = request.getResponseCode() == 200)
                {
                     request.removeResponseListener(this);
                     Dialog.show("Success", "Product deleted!", "OK", null);
                }
                else 
                {
                    Dialog.show("ERROR","Retry Again !","OK",null);
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
  
    }
    
    
    public void Order(int id_panier, int total_panier)
    {
        String url = Statics.BASE_URL +"/panier/order/" + total_panier +"?id="+ id_panier;
        request.setUrl(url);
        request.setPost(true);
        
          request.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
               responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
            
      NetworkManager.getInstance().addToQueueAndWait(request);     
    }
    
     public void OrderAll (int total_panier)
    {
        String url = Statics.BASE_URL +"/panier/orderall/" + total_panier ;
        request.setUrl(url);
        request.setPost(false);
        
          request.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
               responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
            
      NetworkManager.getInstance().addToQueueAndWait(request);     
    }
    
    
    
}
