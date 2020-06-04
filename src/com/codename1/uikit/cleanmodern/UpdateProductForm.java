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
import com.esprit.services.ProductService;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;


/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class UpdateProductForm extends BaseForm {

String  fileName;
Image img2 ;

  ArrayList<Product> pmodifier = new ArrayList<>();
  
        ProductService pserv = new ProductService();
     
    
    public UpdateProductForm(Resources res,int id) {
        
      
        
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Produit");
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
        
         
        pmodifier = pserv.getAllProducts();
       
        for (Product pro:pmodifier)
        {
            if ( pro.getId() == id)
            {
                TextField name = new TextField(pro.getName());
                name.setUIID("TextFieldBlack");
                addStringValue("name", name);
                TextField price = new TextField(Integer.toString(pro.getId()));
                price.setUIID("TextFieldBlack");
                addStringValue("price", price);
                TextField quantity = new TextField(Integer.toString(pro.getQuantity()));
                quantity.setUIID("TextFieldBlack");
                addStringValue("quantity", quantity);
                TextField Description = new TextField(pro.getDescription());
                Description.setUIID("TextFieldBlack");
                addStringValue("Description", Description);
                TextField reference = new TextField(pro.getReference());
                reference.setUIID("TextFieldBlack");
                addStringValue("reference", reference);
                Button add = new Button("Confirm");

            addStringValue("", add);
            
           

          add.addActionListener(e-> {
             ProductService ps = new ProductService();
           if ( ps.UpdateProduct(pro.getId(),name.getText(),Integer.parseInt(price.getText()), Description.getText(), reference.getText(), Integer.parseInt(quantity.getText())))
           {
               Dialog.show("Success","Product Updated Successfully !", "OK", null);
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
