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

import com.codename1.components.ScaleImageLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
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
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Product;
import com.esprit.entities.mecanicien;
import com.esprit.services.MechanicienService;
import com.esprit.services.ProductService;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class UpdateMechanicientForm extends BaseForm {

String  fileName;
Image img2 ;

  ArrayList<mecanicien> pmodifier = new ArrayList<>();
  
        MechanicienService pserv = new MechanicienService();
     
    
    public UpdateMechanicientForm(Resources res,int id) {
        
      
        
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("mechnicien");
        getContentPane().setScrollVisible(false);
        
        
          System.out.println("wssssssol l'id "+id);
        
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
        
        System.out.println("liste modif : "+pmodifier);
        
         
        pmodifier = pserv.getAllMec();
       
        for (mecanicien pro:pmodifier)
        {
            if ( pro.getId() == id)
            {
                TextField Nom = new TextField(pro.getNom());
                
       Nom.setUIID("TextFieldBlack");
        addStringValue("Nom", Nom);
  TextField Prenom = new TextField(pro.getPrenom());
        Prenom.setUIID("TextFieldBlack");
        addStringValue("Prenom", Prenom);
          TextField Experience = new TextField(pro.getExperience());
        Experience.setUIID("TextFieldBlack");
        addStringValue("Experience", Experience);
          TextField Description = new TextField(pro.getDescription());
        Description.setUIID("TextFieldBlack");
        addStringValue("Description", Description);
       
        TextField image = new TextField(pro.getImage());
        image.setUIID("TextFieldBlack");
        addStringValue("image", image);
      
         TextField prix = new TextField(Float.toString(pro.getPrix()));
        prix.setUIID("TextFieldBlack");
        addStringValue("prix", prix);

         TextField Mail = new TextField(pro.getMail());
        Mail.setUIID("TextFieldBlack");
        addStringValue("Mail", Mail);
        
Button add = new Button("update");
            addStringValue("", add);
            
           

          add.addActionListener(e-> {
             MechanicienService ps = new MechanicienService();
           if ( ps.UpdateMecanicien(pro.getId(),Nom.getText(),Prenom.getText(), Integer.parseInt(prix.getText()), Description.getText(), Experience.getText(),Mail.getText()))
           {
               Dialog.show("Success","mechanicien Updated Successfully !", "OK", null);
               new AfficherMesProduitsForm(res).show();
           }
           else {
                Dialog.show("échec","Retry", "OK", null);
           }
         });
             
            }
       
            
        }

     
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));

    }
}
