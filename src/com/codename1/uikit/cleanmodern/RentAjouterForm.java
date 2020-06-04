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
import com.esprit.entities.Rent;
import com.esprit.entities.User;
import com.esprit.services.RentService;
import java.io.IOException;
import java.io.OutputStream;


/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class RentAjouterForm extends BaseForm {

String  fileName;
Image img2 ;
    
    public RentAjouterForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Rent Produit");
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
     TextField model = new TextField("");
        model.setUIID("TextFieldBlack");
        addStringValue("model", model);
       
        TextField price = new TextField("");
        price.setUIID("TextFieldBlack");
        addStringValue("price", price);
          
        TextField quantity = new TextField("");
        quantity.setUIID("TextFieldBlack");
        addStringValue("quantity", quantity);
        
        TextField Description = new TextField("");
        Description.setUIID("TextFieldBlack");
        addStringValue("Description", Description);
        
        TextField localisation = new TextField("");
        localisation.setUIID("TextFieldBlack");
        addStringValue("Localisation", localisation);
        
        TextField marque = new TextField("");
        marque.setUIID("TextFieldBlack");
        addStringValue("marque", marque);
        
         
        TextField days = new TextField("");
        days.setUIID("TextFieldBlack");
        addStringValue("days", days);
        
      
         TextField reference = new TextField("");
        reference.setUIID("TextFieldBlack");
        addStringValue("reference", reference);
        
        
        
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

       
        
        
        
        
        
        
       
        /*TextField image = new TextField("");
        image.setUIID("TextFieldBlack");
        addStringValue("image", image);*/
      
        
        
         Button add = new Button("Ajouter");

       addStringValue("", add);

          add.addActionListener(e-> {
             RentService ps = new RentService();
             //Integer.parseInt(quantity.getText())
             //int quantity, String marque, String reference, String description, String model, String localisation, int days, int prix, String image, int id_user) {

           if (ps.addProduct(new Rent(Integer.parseInt(quantity.getText()), marque.getText(), reference.getText(), Description.getText(), model.getText(), localisation.getText(),Integer.parseInt(days.getText()), Integer.parseInt(price.getText()), fileName, User.connectedUser.getId())))  
           {
               
                String pathToBeStored = "file://C:/wamp64/www/Pi-dev-final/web/uploads/" + fileName;
                                                            OutputStream os;
                 try {
                     os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                      ImageIO.getImageIO().save(img2, os, ImageIO.FORMAT_PNG, 0.9f);
                                                            os.close();
                 } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                 }
                    

               Dialog.show("Success","Product Added Successfully !", "OK", null);
               new ProduitAfficherForm( res).show();
           }
           else {
                Dialog.show("échec","Retry", "OK", null);
           }
         });
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));

    }
}
