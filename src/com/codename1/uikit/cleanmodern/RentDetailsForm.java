/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.codename1.uikit.cleanmodern;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import static com.codename1.uikit.cleanmodern.ResevasionMecForm.ACCOUNT_SID;
import static com.codename1.uikit.cleanmodern.ResevasionMecForm.AUTH_TOKEN;
import static com.codename1.uikit.cleanmodern.ResevasionMecForm.codex;
import com.esprit.entities.Product;
import com.esprit.entities.events;
import com.esprit.entities.Rent;
import com.esprit.services.EventsService;
import com.esprit.services.RentService;
import com.esprit.services.PanierService;
import com.esprit.services.ProductService;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class RentDetailsForm extends BaseForm {
 Form current;
 public static String codex;
    public static final String ACCOUNT_SID = "AC17206e77ae551637b207dba93abedf87";
    public static final String AUTH_TOKEN = "42d2f21e26649f216ec6848a68cba79c";
    String phonenumber= "+21655410596";


     int id_product;
    
    public static Form details;

    RentService ps = new RentService();

    ArrayList<Rent> products = ps.getAllProducts();

    private static RentDetailsForm instance;
public static RentDetailsForm getInstance()
    {
        return instance;
    }
                 
    public RentDetailsForm(int id,Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Rent Product");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("profile-background.jpg");
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

       setLayout(BoxLayout.y());
        for (Rent pro :products){
        
        if (pro.getId()== id){
        
         Container item = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        EncodedImage enco = EncodedImage.createFromImage(res.getImage("load.png"), false);
        String url = "http://localhost/Pi-dev-final/web/uploads/" + pro.getImage();
        Image im = URLImage.createToStorage(enco, pro.getImage(), url);
        ImageViewer imv = new ImageViewer(im);
        item.add(imv);

        Container data = new Container(BoxLayout.y());

       Label lname = new Label("Marque :");
        Font fnt = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        lname.getUnselectedStyle().setFont(fnt);
        SpanLabel tfname = new SpanLabel(pro.getMarque());

        Container nom = new Container(BoxLayout.x());
        nom.add(lname);
        nom.add(tfname);

        data.add(nom);

       Label lreference = new Label("Model:");
        Font fnt2 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        lreference.getUnselectedStyle().setFont(fnt2);
        SpanLabel tfreference = new SpanLabel(pro.getModel());

        Container reference = new Container(BoxLayout.x());
        reference.add(lreference);
        reference.add(tfreference);

        data.add(reference);


        Label lprix = new Label("Prix :");
        Font fnt4 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        lprix.getUnselectedStyle().setFont(fnt4);
        SpanLabel tfprix = new SpanLabel (Float.toString(pro.getPrix()) + "DT");


        Container prix = new Container(BoxLayout.x());
        prix.add(lprix);
        prix.add(tfprix);

        data.add(prix);
      
        
        Label lexperience = new Label("Localisation :");
        Font fnt5 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        lexperience.getUnselectedStyle().setFont(fnt5);
        SpanLabel tfexperience = new SpanLabel(pro.getLocalisation());


  Container Experience = new Container(BoxLayout.x());
        Experience.add(lexperience);
        Experience.add(tfexperience);
        data.add(Experience);
        
         Label lDescription = new Label("Description :");
        Font fnt6 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        lDescription.getUnselectedStyle().setFont(fnt6);
        SpanLabel tfDescription = new SpanLabel(pro.getDescription());

  Container Description = new Container(BoxLayout.x());
        Description.add(lDescription);
        Description.add(tfDescription);

        data.add(Description);
        
        
        Label lMail = new Label("Reference :");
        Font fnt7 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        lMail.getUnselectedStyle().setFont(fnt7);
        SpanLabel tfMail = new SpanLabel(pro.getReference());

         Container Mail = new Container(BoxLayout.x());
        Mail.add(lMail);
        Mail.add(tfMail);
        
        data.add(Mail);
 
        Button checkout = new Button("Reserver");
        Font fnt45 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        checkout.getUnselectedStyle().setFont(fnt45);

         checkout.addActionListener((evt) -> {
           

               
                
                
                System.out.println("=========================");
                String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                StringBuilder salt = new StringBuilder();
                Random rnd = new Random();
                while (salt.length() < 5) { // length of the random string.
                    int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                    salt.append(SALTCHARS.charAt(index));
                }
                String saltStr = salt.toString();
                Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
                com.twilio.rest.api.v2010.account.Message messages = com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber(phonenumber),
                        new PhoneNumber("+18588779208"), "un client veux de contacter : "+phonenumber+"," +saltStr).create();
                
                codex = saltStr;
                System.out.println("======================");
                System.out.println("======================");
                System.out.println("======================");
                System.out.println(codex);
           Dialog.show("succes", "un sms a éte envoyer au mecanicien el te contactera tres bienteaux ", "ok", null);
           new MechAfficherForm(res).show();
            });
          // new MechAfficherDetailsForm(pro.getId(),res);
           
       
       
        data.add(checkout);
        
                

         id_product = pro.getId();
        
      

   
        item.add(data);
        add(item);
        
        
        }
        
        
        }

 
       
    }
    
       
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));

    }

 

}
