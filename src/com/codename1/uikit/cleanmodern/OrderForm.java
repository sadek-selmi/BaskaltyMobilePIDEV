/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Orders;
import com.esprit.services.OrderService;
import com.esprit.services.PaymentService;
import com.stripe.exception.StripeException;


/**
 *
 * @author benha
 */
public class OrderForm extends BaseForm {
    
    Form order;
    
    public OrderForm (Resources theme,int total)
    {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Post");
        getContentPane().setScrollVisible(false);
        
           super.addSideMenu(theme);

        tb.addSearchCommand(e -> {});
        
        
        Image img = theme.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3
                            
                            
                            
                    )
                )
        ));
        order = this;
        setTitle ("order");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        
        TextField name = new TextField("","name");
                name.setUIID("TextFieldBlack");

        TextField email = new TextField("","email");
                        email.setUIID("TextFieldBlack");

        TextField phonenumber = new TextField("","phonenumber");
                        phonenumber.setUIID("TextFieldBlack");

        TextField adresse = new TextField("","adresse");
                        adresse.setUIID("TextFieldBlack");

        TextField city = new TextField("","city");
                        city.setUIID("TextFieldBlack");

        SpanLabel totalapayer = new SpanLabel ("Total Card :" + String.valueOf(total) +"DT");
                        totalapayer.setUIID("TextFieldBlack");

        Button confirmpayment = new Button("Proceed to payment");
        
       confirmpayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
          // OrderService ord = new OrderService();
           if (Dialog.show("", "Add order ?", "Confirm", "Cancel"))
           {
                PaymentService ps = new PaymentService();
                try {
                 String id_customer = ps.createCustomer(city.getText(), adresse.getText(), name.getText(), email.getText(), phonenumber.getText());
          
                   Dialog.show("","Order created successfully !","OK",null);
                     new PaymentForm(String.valueOf(total),id_customer, theme).show();
                } catch (StripeException ex) {
                    Dialog.show("error",ex.getMessage(),"OK",null);
                }
             
               
           }
          // ord.AddOrder(new Orders(name.getText(), email.getText(), phonenumber.getText(), adresse.getText(), city.getText(),total));
           
               
          
            }
        });
        
        addAll(name,email,phonenumber,adresse,city,totalapayer,confirmpayment);
       
       
    }
     private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));

    }
}
