/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Component;

import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

import com.codename1.ui.Font;
import com.codename1.ui.FontImage;

import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.services.PaymentService;
import com.stripe.exception.StripeException;


/**
 *
 * @author benha
 */
public class PaymentForm extends BaseForm{
    Form payment;
    
    public PaymentForm(String total_a_payer,String customer_id,Resources theme)
    {   super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Post");
        getContentPane().setScrollVisible(false);
        
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = theme.getImage("profile-background.jpg");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
       super.addSideMenu(theme);

        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3
                            
                            
                            
                    )
                )
        ));
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        payment = this;
        setTitle("Payment Form");
        
        Label welcome =new Label("Complete your payment operation");
        Font fontpayment = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
        Style stylepayment = welcome.getAllStyles();
        stylepayment.setMarginTop(80);
        stylepayment.setFont(fontpayment);
       Container centercompleteyourpayment = FlowLayout.encloseCenter(welcome);
   
      Label conpay = new Label("Pay By :");
      Font font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
      Style stylepay = conpay.getAllStyles();
      stylepay.setFont(font);
      stylepay.setMarginTop(100);
      stylepay.setMarginLeft(140);
      stylepay.setMarginBottom(50);
   
      ImageViewer master = new ImageViewer();
      master.setImage(theme.getImage("credid-card.png"));
      Style styleimage = master.getAllStyles();
      styleimage.setMarginBottom(80);
   
      Label cardnumber = new Label("Card Number");
             cardnumber.setUIID("TextFieldBlack");

      Font fcartnumber = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
      Style scart = cardnumber.getAllStyles();
      scart.setFont(fcartnumber);
      TextField tfcardnumber = new TextField("","0000-0000-0000-0000");
                tfcardnumber.setUIID("TextFieldBlack");

      Label lmonth = new Label("Month");
                   lmonth.setUIID("TextFieldBlack");

      lmonth.getUnselectedStyle().setFont(font);
      Label lyear = new Label("Year");
                   lyear.setUIID("TextFieldBlack");

      lyear.getUnselectedStyle().setFont(font);
      Label lcvc = new Label("CVC");
                   lcvc.setUIID("TextFieldBlack");

     lcvc.getUnselectedStyle().setFont(font);
       Container datedard = BoxLayout.encloseX(lmonth,lyear,lcvc);
   
      TextField tfmonth = new TextField("","00",2,TextArea.NUMERIC);
                       tfmonth.setUIID("TextFieldBlack");

      TextField tfyear = new TextField("","00",2,TextArea.NUMERIC);
                             tfyear.setUIID("TextFieldBlack");

      TextField tfcvc = new TextField("","CVC",2,TextArea.PASSWORD);
                             tfcvc.setUIID("TextFieldBlack");

   
      Container textfieldscontainer = BoxLayout.encloseX(tfmonth,tfyear,tfcvc);
      
     
      
      Button confirmpayment = new Button("Confirm Payment");
      
      confirmpayment.addActionListener(e->{ 
        if((tfcardnumber.getText().isEmpty())||(tfmonth.getText().isEmpty())||(tfyear.getText().isEmpty()) || (tfcvc.getText().isEmpty()))
        {
            Dialog.show("Error","pls verify your card number or your code","ok",null);
            }
       else{
            if (Dialog.show("Complete Your Payment", "  Are you sure for Paying this contract ?", "Confirm", "Cancel"))
            {
                PaymentService pser = new PaymentService();
                try {
                 pser.AttachCardToCustomer(tfcardnumber.getText(),Integer.parseInt(tfmonth.getText()),Integer.parseInt(tfyear.getText()),tfcvc.getText(), customer_id);
                    pser.chargeCreditCard(total_a_payer, customer_id);
                    ToastBar.showMessage("Payment Succeeded !", FontImage.MATERIAL_CHECK,15);
                } catch (StripeException ex) {
                  Dialog.show("error",ex.getMessage(),"OK",null);
                }
                
            }
        }
            
      });
      
  
        add(BoxLayout.encloseY(centercompleteyourpayment,conpay,master,cardnumber,tfcardnumber,datedard,textfieldscontainer,confirmpayment));
         getToolbar().addMaterialCommandToLeftSideMenu("",FontImage.MATERIAL_MENU,new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    
                }
            });
     
        
    }
    
}
