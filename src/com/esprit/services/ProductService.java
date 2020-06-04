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
import com.esprit.entities.Product;
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
public class ProductService {

    private ConnectionRequest request;

    private boolean responseResult;
    ArrayList<Product> products = new ArrayList<>();

    public ProductService() {
        request = DataSource.getInstance().getRequest();
    }

    public boolean addProduct(Product p) {
        String url = Statics.BASE_URL + "/products/add?name=" + p.getName() + "&price=" + p.getPrice()
                + "&image=" + p.getImage() + "&description=" + p.getDescription() + "&reference=" + p.getReference()
                + "&quantite=" + p.getQuantity()+"&iduser="+User.connectedUser.getId();

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

    public ArrayList<Product> parseProducts(String jsonText) {
        try {

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {

                int id = (int) Float.parseFloat(obj.get("id").toString());
                String name = obj.get("name").toString();
                int quantity = (int) Float.parseFloat(obj.get("quantite").toString());
                String reference = obj.get("reference").toString();
                String description = obj.get("description").toString();
                String image = obj.get("image").toString();
                int price = (int) Float.parseFloat(obj.get("price").toString());
                products.add(new Product(id, quantity, name, reference, description, price, image));
            }

        } catch (IOException ex) {
        }

        return products;
    }

    public boolean SearchProduct(int id) {
        String url = Statics.BASE_URL + "/products/find/" + id;
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200;
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;

    }

    public ArrayList<Product> searchProduct2(int id) 
    {
        String url = Statics.BASE_URL + "/products/find/" + id;
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
    
     public ArrayList<Product> getProductsById(int id_user) {
        String url = Statics.BASE_URL + "/products/getByUser/"+id_user;
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
    
    
    
    

    public ArrayList<Product> getAllProducts() {
        String url = Statics.BASE_URL + "/products/all";
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

    public ArrayList<Product> parseProductDetails(String json) {

        ArrayList<Product> listTasks = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> articles = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) articles.get("root");

            for (Map<String, Object> obj : list) {

                Product pro = new Product();
                int id = (int) Float.parseFloat(obj.get("id").toString());
                pro.setId((int) id);
                pro.setImage((String) obj.get("name"));
                pro.setDescription((String) obj.get("description"));
                pro.setReference((String) obj.get("reference"));
                pro.setImage((String) obj.get("image"));
                pro.setPrice((int) Float.parseFloat(obj.get("price").toString()));
                pro.setQuantity((int) Float.parseFloat(obj.get("quantite").toString()));

                System.out.println(pro.toString());

                listTasks.add(pro);

            }

        } catch (IOException ex) {
        }
        System.out.println(listTasks);
        return listTasks;
    }

    /*   public ArrayList<Product> SearchProduct (int id) {
       //ArrayList<Product> pdetails = new ArrayList<>();
        String url = Statics.BASE_URL + "/products/find/"+id;
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               // ProductService ser = new ProductService();
                products = parseProductDetails(new String(request.getResponseData()));
                 request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return products;
    }*/
 /*  public ArrayList<Product> SearchProduct(int id)
        {
     
       String url = Statics.BASE_URL + "/products/find/"+id;
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
    }*/
    public void DeleteProduct (int id) {

        String url = Statics.BASE_URL + "/products/delete?id="+id;
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

    public boolean UpdateProduct(int id,String name,int price,String description,String reference,int quantity) {
        String url = Statics.BASE_URL + "/products/edit?id=" + id + "&name=" + name + "&price=" + price
                + "&description=" + description + "&reference=" + reference
                + "&quantite=" + quantity;

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
