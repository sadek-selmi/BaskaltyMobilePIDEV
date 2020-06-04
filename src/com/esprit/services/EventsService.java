/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.comment;
import com.esprit.entities.Participer;

import com.esprit.entities.events;
import com.esprit.utils.DataSource;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author selmi
 */
public class EventsService {
     private ConnectionRequest request;

    private boolean responseResult;
    ArrayList<events> Events = new ArrayList<>();
    ArrayList<comment> Commenter = new ArrayList<>();
    ArrayList<Participer> Par = new ArrayList<>();

    public EventsService() {
         request = DataSource.getInstance().getRequest();
    }
    
    
        public boolean addEvent(events p) {
        String url = Statics.BASE_URL + "/AddM?name=" + p.getTitre()+ "&location=" +p.getLocation()+"&idevent=" +p.getId_User()+
                "&image="+p.getImage()+"&description="+p.getContenu()+"&start="+p.getStart()+"&end="+p.getEnd()+"&prix="+p.getPrix()+"&themeid="+1
                +"&quantity="+ p.getQuantity();

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
     
       public ArrayList<events> parseProducts (String jsonText) {
        try {
        
            JSONParser jp = new JSONParser(); 
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                
               int id = (int)Float.parseFloat(obj.get("id").toString());
               
               String name = obj.get("name").toString();
             
               
               String location  = obj.get("location").toString();
           
               
               String description = obj.get("description").toString();
                             String start = obj.get("start").toString();
               String end = obj.get("end").toString();

                              int intersted = (int) Float.parseFloat(obj.get("Interstednumber").toString());
                              int participer = (int) Float.parseFloat(obj.get("Participernumber").toString());

                              String image = obj.get("image").toString();

               int prix = (int) Float.parseFloat(obj.get("prix").toString());
                              int quantity = (int) Float.parseFloat(obj.get("quantity").toString());

                Events.add(new events(id, name, description, location, start, image, end, prix, quantity,intersted,participer));
            }

        } catch (IOException ex) {
        }

        return Events;
    }
       
       public ArrayList<events> parseProductss (String jsonText) {
        try {
        
            JSONParser jp = new JSONParser(); 
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                
               int id = (int)Float.parseFloat(obj.get("id").toString());
               
               String name = obj.get("name").toString();
             
               
               String location  = obj.get("location").toString();
           
               
               String description = obj.get("description").toString();
                             String start = obj.get("start").toString();
               String end = obj.get("end").toString();

                            

                              String image = obj.get("image").toString();

               int prix = (int) Float.parseFloat(obj.get("prix").toString());
                              int quantity = (int) Float.parseFloat(obj.get("quantity").toString());

                Events.add(new events(id, name, description, location, start, image, end, prix, quantity));
            }

        } catch (IOException ex) {
        }

        return Events;
    }


       
       
       
        public ArrayList<events> getAllEvents() {
        String url = Statics.BASE_URL + "/Affichermobile";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Events = parseProducts(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Events;
    }
    
 
         public ArrayList<events> getAllEventdetials(int id ) {
        String url = Statics.BASE_URL + "/detailsM/"+id;
        request.setUrl(url);
        request.setPost(false);
      
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Events;
    }  
       public ArrayList<comment> getAllcommenter() {
        String url = Statics.BASE_URL + "/afff";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Commenter = parsecomenter(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Commenter;
    }  public ArrayList<comment> parsecomenter (String jsonText) {
        try {
        
            JSONParser jp = new JSONParser(); 
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                
               int id = (int)Float.parseFloat(obj.get("id").toString());
               
                              int ents = (int)Float.parseFloat(obj.get("id").toString());


               
           
               
               String content = obj.get("content").toString();
    String username = obj.get("username").toString();
    String imageFile = obj.get("imageFile").toString();

                

                Commenter.add(new comment(id, content, ents,username,imageFile));
            }

        } catch (IOException ex) {
        }

        return Commenter;
    }
       
       public boolean addCommenter(comment p) {
        String url = Statics.BASE_URL + "/Addd?idevent="+p.getIdevent()+"&content="+p.getContent()+"&user="+p.getIduser();

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
       public void DeleteEvent (int id) {

        String url = Statics.BASE_URL + "/Events/delete?id="+id;
        request.setUrl(url);
        request.setPost(true);
        
        request.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (responseResult = request.getResponseCode() == 200)
                {
                     request.removeResponseListener(this);
                     ToastBar.showMessage("Product deleted Successfully !",FontImage.MATERIAL_DONE, 5);
                
                }
                else 
                {
                     ToastBar.showMessage("Error,Try again !",FontImage.MATERIAL_ERROR, 5);
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
  
    }
   // new events(1, Title.getText(), Description.getText(), adresse.getText(),Debut.getText(),end.getText(), Integer.parseInt(prix.getText()), Integer.parseInt(quantity.getText())));    
       public boolean Updateevent(int id,String title,String description,String adresse,String start,String end, int prix,int quantity,String image) {;
        String url = Statics.BASE_URL + "/editevent?id=" + id + "&name=" + title + "&prix=" + prix
                + "&description=" + description + "&location=" + adresse + "&start=" + start + "&end=" + end
                + "&quantity=" + quantity+"&image=" + image;

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
        public boolean Intersted(int event,int user) {;
        String url = Statics.BASE_URL + "/Interstedm?event=" + event + "&user=" + user ;

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

    public ArrayList<events> getEventsById(int id_user) {
        String url = Statics.BASE_URL + "/eventss/"+id_user;
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Events = parseProductss(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Events;
    }
    
     public boolean Confirmatin (int event,int user,String code ) {;
        String url = Statics.BASE_URL + "/envoyerConfirmationmobile?event=" + event + "&user=" + user +"&code="+code;

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
     

               public ArrayList<Participer> champs (int event,int user ) {;
        String url = Statics.BASE_URL + "/chams?id="+event+"&event="+user;
 request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Par = parsechams(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Par;
    }
                         public ArrayList<Participer> champss (int event,int user ) {;
        String url = Statics.BASE_URL + "/chamss?id="+user+"&event="+event;
 request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Par = parsechamsss(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Par;
    }
               public ArrayList<Participer> parsechams (String jsonText) {
        try {
        
            JSONParser jp = new JSONParser(); 
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                
              
    String chams = obj.get("champsConfirmation").toString();

                

                Par.add(new Participer(chams));
            }

        } catch (IOException ex) {
        }

        return Par;
    }
                 public ArrayList<Participer> parsechamsss (String jsonText) {
        try {
        
            JSONParser jp = new JSONParser(); 
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                
              
    String chams = obj.get("confirmation").toString();

                

                Par.add(new Participer(chams));
            }

        } catch (IOException ex) {
        }

        return Par;
    }
                  public boolean Participer(int event,int user) {;
        String url = Statics.BASE_URL + "/participerusermobile?event=" + event + "&user=" + user ;

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
                  
               public ArrayList<Participer> Participerd (int event,int user ) {;
        String url = Statics.BASE_URL + "/participerusermobile?event=" + event + "&user=" + user ;
 request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Par = parsechamsss(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Par;
    }
               public ArrayList<Participer > getAll() {
       
        ArrayList<Participer> listParticipation = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/Pi-dev-final/web/app_dev.php/api/partii");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    //System.out.println(tasks);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {
                       
                        Participer p =new Participer();
  
                        p.setId((int) Float.parseFloat(obj.get("id").toString()));
                        
                        LinkedHashMap<String,Object> objEvenement= (LinkedHashMap<String,Object> ) obj.get("eventtP");
                        p.setId_event((int) Float.parseFloat(objEvenement.get("id").toString()));
                        
                        LinkedHashMap<String,Object> objUser= (LinkedHashMap<String,Object> ) obj.get("user");
                        p.setId_user((int)Float.parseFloat(objUser.get("id").toString()));
                       
                        p.setChamps( obj.get("confirmation").toString());

                        listParticipation.add(p);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listParticipation;
    }
               public boolean findparticipation(Participer p)
    {
        
        for (int i=0;i<getAll().size();i++)
        {
            if((p.getId_event()==getAll().get(i).getId_event()) &&(p.getId_user()==getAll().get(i).getId_user())  )
            {
                 return true;       
            }
        }
        return false;
    }
               
               String ss ="vide";
  
                 
               public boolean findparticipationC(Participer p)
    {
        
        for (int i=0;i<getAll().size();i++)
        {  
            if((p.getId_event()==getAll().get(i).getId_event()) &&(p.getId_user()==getAll().get(i).getId_user())  )
            {
                System.out.println(" getAll().get(i).getChamps()"+getAll().get(i).getChamps());
                if(getAll().get(i).getChamps()=="true")
                {
                              return true;       

                }
                
            }
        }
        return false;
    }
               public boolean PDFF(int idevent , int user ) {
        String url = Statics.BASE_URL + "/pdff?event="+idevent+"&user="+user;
                   System.out.println("edldl"+url);
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
             
             }
