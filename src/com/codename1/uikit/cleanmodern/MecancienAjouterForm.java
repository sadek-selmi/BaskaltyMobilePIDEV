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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Product;
import com.esprit.entities.events;
import com.esprit.entities.mecanicien;
import com.esprit.services.EventsService;
import com.esprit.services.MechanicienService;
import com.esprit.services.ProductService;
import java.io.IOException;
import java.io.OutputStream;


/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class MecancienAjouterForm extends BaseForm {
     String path;
String  fileName;
Container imgCtn;
Image img2 ;
ImageViewer l = new ImageViewer();
    public MecancienAjouterForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Mechancien");
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

        
          
        
        
        TextField Nom = new TextField("");
       Nom.setUIID("TextFieldBlack");
        addStringValue("Nom", Nom);
  TextField Prenom = new TextField("");
        Prenom.setUIID("TextFieldBlack");
        addStringValue("Prenom", Prenom);
          TextField Experience = new TextField("");
        Experience.setUIID("TextFieldBlack");
        addStringValue("Experience", Experience);
          TextField Description = new TextField("");
        Description.setUIID("TextFieldBlack");
        addStringValue("Description", Description);
       
           final String[] jobPic = new String[1];
        Label jobIcon = new Label();
        Button image = new Button("Ajouter une image ");
        addStringValue("image", image);

        final String[] image_name = {""};
        final String[] filePath = {""};
        fileName = "";
        image.addActionListener((ActionEvent actionEvent) -> {
            Display.getInstance().openGallery(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ev) {

                    filePath[0] = (String) ev.getSource();
                    System.out.println(ev.getSource());
                    int fileNameIndex = filePath[0].lastIndexOf("/") + 1;
                    fileName = filePath[0].substring(fileNameIndex);
    
                    try {
                        img2 = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath[0]));
                    
                        jobIcon.setIcon(img2);
                     
                    } catch (Exception e) {
                       
                    }
                }
            }, Display.GALLERY_IMAGE);
        });

      
         TextField prix = new TextField("");
        prix.setUIID("TextFieldBlack");
        addStringValue("prix", prix);
     
         TextField region = new TextField("");
        region.setUIID("TextFieldBlack");
        addStringValue("region", region);
     
TextField city = new TextField("");
        city.setUIID("TextFieldBlack");
        addStringValue("city", city);
     
        TextField Adomicile = new TextField("");
        Adomicile.setUIID("TextFieldBlack");
        addStringValue("Adomicile", Adomicile);
       
         TextField telephone = new TextField("");
        Adomicile.setUIID("TextFieldBlack");
        addStringValue("telephone", telephone);
        
         TextField Mail = new TextField("");
        Mail.setUIID("TextFieldBlack");
        addStringValue("Mail", Mail);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        Button add = new Button("Ajouter");

       addStringValue("", add);
  add.addActionListener(e-> {
             MechanicienService ps = new MechanicienService();
    //public events(int Id_User, String Titre, String Contenu, String location,prix.getText()), Integer.parseInt(quantity.getText()) String image, Date debut, Date fin, int prix, int quantity, theme theme) {
         //public mecanicien(Service service, String nom, String prenom, String description, String mail, String image, float prix, int num_tel, String experience, String region, String city, int adomicile) {

           if (ps.addMechancien(new mecanicien(Nom.getText(), Prenom.getText(), Description.getText(), Mail.getText(),fileName, Float.parseFloat(prix.getText()), Integer.parseInt(telephone.getText()), Experience.getText(), region.getText(), city.getText(), Integer.parseInt(Adomicile.getText()))));  
           {

                try {
                    String pathToBeStored = "file://C:/wamp64/www/Pi-dev-final/web/uploads/" + fileName;
                    OutputStream os;
                    os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                    ImageIO.getImageIO().save(img2, os, ImageIO.FORMAT_PNG, 0.9f);
                    os.close();
                    
                    
                    
                    
                    
                    
                } catch (IOException ex) {
                }

                    Dialog.show("Success","Mechancien Added Successfully !", "OK", null);
                    new MechAfficherForm(res).show();
           }
           
         });
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));

    }
}
