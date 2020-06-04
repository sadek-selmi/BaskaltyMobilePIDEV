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
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.uikit.cleanmodern.AfficherMesProduitsForm;
import com.esprit.entities.Rent;
import com.esprit.entities.User;
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
public class RentService {

    private ConnectionRequest request;

    private boolean responseResult;
    ArrayList<Rent> products = new ArrayList<>();

    public RentService() {
        request = DataSource.getInstance().getRequest();
    }

    public boolean addProduct(Rent p) {
        String url = Statics.BASE_URL + "/addrent?quantity=" + p.getQuantity()+ "&price=" + p.getPrix()
                + "&image=" + p.getImage() + "&description=" + p.getDescription() + "&reference=" + p.getReference()+ "&marque=" + p.getMarque()+ "&model=" + p.getModel()+ "&days=" + p.getDays()
                + "&localisation=" + p.getLocalisation()+"&iduser="+User.connectedUser.getId();

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

    public ArrayList<Rent> parseProducts(String jsonText) {
        try {

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
  //int id, int quantity, String marque, String reference, String description, String model, String localisation, int days, int prix, String image, int id_user) {

                int id = (int) Float.parseFloat(obj.get("id").toString());
                int quantity = (int) Float.parseFloat(obj.get("quantity").toString());
                String reference = obj.get("reference").toString();
                                String marque = obj.get("marke").toString();

                String description = obj.get("description").toString();
                                String model = obj.get("model").toString();
                                                String localisation = obj.get("localisation").toString();
                int days = (int) Float.parseFloat(obj.get("Rentdays").toString());


                String image = obj.get("image").toString();
                int prix = (int) Float.parseFloat(obj.get("price").toString());
                products.add(new Rent(id, quantity,marque,reference , description, model,localisation,days,prix, image));
            }

        } catch (IOException ex) {
        }

        return products;
    }


    
    

    public ArrayList<Rent> getAllProducts() {
        String url = Statics.BASE_URL + "/readrent";
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseProducts(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return products;
    }

}
