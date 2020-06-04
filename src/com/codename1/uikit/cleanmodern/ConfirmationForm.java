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

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

import com.codename1.ui.Image;
import com.codename1.ui.Label;

import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;

import com.codename1.ui.plaf.Style;

import com.codename1.ui.util.Resources;
import com.esprit.entities.Participer;

import com.esprit.entities.User;
import com.esprit.entities.events;
import com.esprit.entities.comment;

import com.esprit.services.EventsService;

import java.util.ArrayList;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ConfirmationForm extends BaseForm {

    int id_product;
    int s = 0;
 private ImageViewer photoProfilViewer;
    private Image imgss;
    EventsService ps = new EventsService();

    ArrayList<events> Events = ps.getAllEvents();


    private static ConfirmationForm instance;

    public static ConfirmationForm getInstance() {
        return instance;
    }

    public ConfirmationForm(Resources res, int id ) {
       super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Confirmation");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });

        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
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
             
             
             
             
    ArrayList<Participer> arr = ps.champs(User.connectedUser.getId(),id);


           
        
    
    for (events p : Events) {
            if(p.getId_User()==id){
      

             add(AddItems(p, res,id,arr));
            }
           
        }
    }

    public Container AddItems(events pro, Resources theme,int id,ArrayList<Participer> champs) {
        Container item = new Container(new BoxLayout(BoxLayout.X_AXIS_NO_GROW));

        System.out.println("event"+pro.getId_User()+"user"+User.connectedUser.getId());
        Container data = new Container(BoxLayout.yCenter());
              Button PDF = new Button("Downlod");
PDF.setVisible(false);
       addStringValue("", PDF);

          PDF.addPointerPressedListener(e-> {
      
            ps.PDFF(pro.getId_User(), User.connectedUser.getId());
          });
        
Label tfname = new Label("Code Confirmation");
     
    tfname.setUIID("Textsofz");
    data.add(tfname);
      TextField Title = new TextField("");
       Title.setUIID("TextFieldBlack");
        addStringValue("Saisir votre code Mail  ", Title);
        
         Button add = new Button("Valider");

       addStringValue("", add);

          add.addActionListener(e-> {
             EventsService ps = new EventsService();
        if (Title.getText().equals("")) {
                Dialog.show("champs vides", "Titre Manquant ", "ok", null);

            }if(Title.getText().equals(champs.get(0).getChamps())) {
            //ps.PDFF(pro.getId_User(), User.connectedUser.getId());
               ps.Confirmatin(pro.getId_User(), User.connectedUser.getId(), Title.getText());  
        
               
                                                            Dialog.show("Success"," ", "OK", null);
                                                          //  new EventAfficherForm(theme).show();
                                                          Title.setVisible(false);
                                                          
 PDF.setVisible(true );
            }
        
        else {
         Dialog.show("Echecc","Code no valider !", "OK", null);
                                
                      
            }  });
        
        
         
    
        
        
        
        
        
        
        
        item.add(data);
        return item;
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xE06666));

    }

}
