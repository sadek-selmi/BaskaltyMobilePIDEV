/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.Orders;
import com.esprit.utils.DataSource;
import com.esprit.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author benha
 */
public class OrderService {
    
    private ConnectionRequest request;
    
    private boolean responseResult;
    ArrayList<Orders> orders = new ArrayList<>();
    
    public OrderService()
    {
        request= DataSource.getInstance().getRequest();
    }
    
    
    public boolean AddOrder(Orders o)
    {
          String url = Statics.BASE_URL + "/orders/add?name="+o.getNom()+"&email="+ o.getEmail() +
                  "&phonenumber="+o.getPhonenumber()+"&adresse="+o.getAdresse()+"&city="+o.getCity()
                  +"&total="+o.getTotal();

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
