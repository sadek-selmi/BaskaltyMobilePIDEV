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
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.Publication;
import com.esprit.utils.DataSource;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author selmi
 */
public class PublicationService {
      private ConnectionRequest request;

    private boolean responseResult;
    ArrayList<Publication> Events = new ArrayList<>();

    public PublicationService() {
         request = DataSource.getInstance().getRequest();
    }
    
    public boolean addPublication(Publication p) {
        String url = Statics.BASE_URL3 + "/addpostM?image="+p.getImage()+"&contenu="+p.getContenu();

        request.setUrl(url);
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
     
     
       public ArrayList<Publication> parsepubl (String jsonText) {
        try {
        
            JSONParser jp = new JSONParser(); 
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                
               int id = (int)Float.parseFloat(obj.get("id").toString());
               
               String contenu = obj.get("contenu").toString();
             
               
              
               
                              String image = obj.get("image").toString();

              

                Events.add(new Publication(id, image, contenu));
            }

        } catch (IOException ex) {
        }

        return Events;
    }
       


       
       
       
        public ArrayList<Publication> getAllEvents() {
        String url = Statics.BASE_URL3 + "/Aff";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Events = parsepubl(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Events;
    }
    
        
     
         
}
