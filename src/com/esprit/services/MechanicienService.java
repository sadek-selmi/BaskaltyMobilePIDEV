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
import com.esprit.entities.Product;
import com.esprit.entities.User;
import com.esprit.entities.mecanicien;
import com.esprit.utils.DataSource;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.esprit.entities.mecanicien;

/**
 *
 * @author yaya
 */
public class MechanicienService {
    
    private ConnectionRequest request;

    private boolean responseResult;
    ArrayList<mecanicien> products = new ArrayList<>();

    public MechanicienService() {
        request = DataSource.getInstance().getRequest();
    }
       public ArrayList<mecanicien> getMechanicienById(int id_user) {
        String url = Statics.BASE_URL1 + "/findMecanicienbByUser/"+id_user;
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseMec(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return products;
    }
    
public ArrayList<mecanicien> parseMec(String jsonText) {
        try {

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            System.out.println("istmtaaii "+list);
            for (Map<String, Object> obj : list) {



                int id = (int) Float.parseFloat(obj.get("id").toString());
                String nom = obj.get("nom").toString();
                String prenom = obj.get("prenom").toString();

                String mail = obj.get("mail").toString();
                String description = obj.get("description").toString();
                String image = obj.get("image").toString();
                float prix = Float.parseFloat(obj.get("prix").toString());
                String experience = obj.get("experience").toString();
               //public mecanicien(int id, int service, String nom, String prenom, String description, String mail, String image, String prix, float num_tel, String experience, String region, String city, int adomicile)
                products.add(new mecanicien(id,nom, prenom, description, mail, image,prix, experience));
            }

        } catch (IOException ex) {
        }

        return products;
    }
public ArrayList<mecanicien> getAllMec() {
        String url = Statics.BASE_URL1 + "/all";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseMec(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return products;
    }


public boolean addMechancien(mecanicien p) {
        String url = Statics.BASE_URL1 + "/addMob?nom=" + p.getNom()+ "&prenom=" +p.getPrenom()+
                "&image="+p.getImage()+"&mail="+p.getMail()+"&prix="+p.getPrix()+"&num_tel="+p.getNum_tel()+"&description="+p.getDescription()+"&exprience="+p.getExperience()
                +"&region="+ p.getRegion()+"&adomicile="+ p.getAdomicile()+"&city="+ p.getCity()+"&iduser="+User.connectedUser.getId();

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
    
    public ArrayList<mecanicien> getAllMecdetials(int id ) {
        String url = Statics.BASE_URL1 + "/DetailsMob/"+id;
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                MechanicienService s = new MechanicienService();
                products = s.getListTask(new String(request.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return products;
    }
    public ArrayList<mecanicien> getListTask(String json) {

        ArrayList<mecanicien> AnnonceList = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> Annonce = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println("heeyeyee anooononon"+Annonce);

          

                mecanicien a = new mecanicien();
                //    AnnonceList.add(obj.get("titreAnnonce").toString());
            System.out.println("heeyeyee odkhhlhl"+Annonce.get("nom").toString());

                a.setNom(Annonce.get("nom").toString());
                a.setPrenom(Annonce.get("prenom").toString());
                a.setDescription(Annonce.get("description").toString());
                a.setPrix(Float.parseFloat(Annonce.get("prix").toString()));
                //a.setTel_annonce(obj.get("telAnnonce").toString());
                

                AnnonceList.add(a);

            
        } catch (IOException ex) {
        }
        System.out.println(AnnonceList);
        return AnnonceList;
        
    }
         public boolean UpdateMecanicien(int id,String name,String prenom ,int prix,String description,String experience,String mail) {
        String url = Statics.BASE_URL1 +"/edit?id="+id+"&nom="+name+"&prenom="+prenom+"&mail="+mail+"&prix="+prix+"&description="+description+"&exprience="+experience;

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
         
         public void Deletemechanicien(int id) {

        String url = Statics.BASE_URL1 + "/deleteMec?id="+id;
        request.setUrl(url);
        request.setPost(true);
        
        request.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                if (responseResult = request.getResponseCode() == 200)
                {
                     request.removeResponseListener(this);
                     ToastBar.showMessage("mecanicien deleted Successfully !",FontImage.MATERIAL_DONE, 5);
                
                }
                else 
                {
                     ToastBar.showMessage("Error,Try again !",FontImage.MATERIAL_ERROR, 5);
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
  
    }

    }
