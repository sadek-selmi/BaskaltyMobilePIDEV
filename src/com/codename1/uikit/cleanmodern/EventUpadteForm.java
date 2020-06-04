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
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.MultipartRequest;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
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
import com.esprit.services.EventsService;
import com.esprit.services.ProductService;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;



/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class EventUpadteForm extends BaseForm {
String path;
String  fileName;
Container imgCtn;
Image img2 ;
ImageViewer l = new ImageViewer();
MultipartRequest cr = new MultipartRequest();
EventsService ps = new EventsService();
  ArrayList<events> pmodifier = new ArrayList<>();

    public EventUpadteForm(Resources res,int id ) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Evenement");
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

        

      
           
        pmodifier = ps.getAllEvents();
                       System.err.println("eeeee"+id);

        for ( events pro:pmodifier)
        {
            
            if ( pro.getId_User()== id)
            {
                
        
                
                
                
                final String[] jobPic = new String[1];
                                        Label jobIcon = new Label();
                                        Button image = new Button("Update  image ");
                                                addStringValue("image", image);
                                                
                                              Label imgName = new Label(pro.getImage());
                                              imgName.setUIID("TextFieldBlack");
        addStringValue("Name image", imgName);
                                 
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
                                                    //Image img2 = null;
                                                    try {
                                                        img2 = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath[0]));
                                                        //image_name[0] = System.currentTimeMillis() + ".png";
                                                        jobIcon.setIcon(img2);
                                                    imgName.setText(fileName);
                                                        /*String pathToBeStored = FileSystemStorage.getInstance().getAppHomePath() + fileName;
                                                            OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                                                            ImageIO.getImageIO().save(img2, os, ImageIO.FORMAT_PNG, 0.9f);
                                                            os.close();*/
                                                        //System.out.println(pathToBeStored);
                                                    } catch (Exception e) {
                                                        //e.printStackTrace();
                                                        //ToastBar.showErrorMessage("Veuillez importer une image valide !");
                                                    }
                                                }
                                            }, Display.GALLERY_IMAGE);
                                        });
        
        
        
                              


                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                
                                                

        TextField Title = new TextField(pro.getTitre());
       Title.setUIID("TextFieldBlack");
        addStringValue("Title", Title);
  TextField adresse = new TextField(pro.getLocation());
        adresse.setUIID("TextFieldBlack");
        addStringValue("adresse", adresse);
          TextField quantity = new TextField(""+ pro.getQuantity());
        quantity.setUIID("TextFieldBlack");
        addStringValue("quantity", quantity);
          TextField Description = new TextField(pro.getContenu());
        Description.setUIID("TextFieldBlack");
        addStringValue("Description", Description);

        TextField prix = new TextField(""+pro.getPrix());
        prix.setUIID("TextFieldBlack");
        addStringValue("prix", prix);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

      Picker Debut = new Picker();
      Debut.setType(Display.PICKER_TYPE_DATE_AND_TIME);
      Debut.setFormatter(dateFormatter);

                      Debut.setUIID("TextFieldBlack");
                      addStringValue("Debut", Debut);

        Picker end = new Picker();
                end.setUIID("TextFieldBlack");
end.setType(Display.PICKER_TYPE_DATE_AND_TIME);
      end.setFormatter(dateFormatter);

                addStringValue("Fin", end);

         Button add = new Button("Update");

       addStringValue("", add);

          add.addActionListener(e-> {
             EventsService ps = new EventsService();
    //public events(int Id_User, String Titre, String Contenu, String location, String image, Date debut, Date fin, int prix, int quantity, theme theme) {
        if (Title.getText().equals("")) {
                Dialog.show("champs vides", "Titre Manquant ", "ok", null);

            } else if (Description.getText().equals("")) {
                Dialog.show("champs vides", "Description Manquant ", "ok", null);

            } else if (adresse.getText().equals("")) {
                Dialog.show("champs vides", "adresse Manquant ", "ok", null);

            } else if (prix.getText().equals("")) {
                Dialog.show("champs vides", "prix Manquant ", "ok", null);

            } else if (quantity.getText().equals("")) {
                Dialog.show("Champs vides", "quantity Manquant ", "ok", null);

            } else {
            
               try{
                   
                   
                                                          if (fileName.equals("")) {
                                                                    fileName = imgName.getText();
                                                          }
                                                          else if (!fileName.equals(imgName)) {
                                                                            String pathToBeStoredd = "file://C:/wamp64/www/Pi-dev-final/web/uploads/" + fileName;
                                                                            OutputStream oss = FileSystemStorage.getInstance().openOutputStream(pathToBeStoredd);
                                                                            ImageIO.getImageIO().save(img2, oss, ImageIO.FORMAT_PNG, 0.9f);
                                                                            File f = new File("file://C:/wamp64/www/Pi-dev-final/web/uploads/" + imgName);
                                                                            f.delete();
                                                                            oss.close();
                                                                            
                                                                        }
                                 ps.Updateevent(pro.getId_User(), Title.getText(), Description.getText(), adresse.getText(),Debut.getText(),end.getText(), Integer.parseInt(prix.getText()), Integer.parseInt(quantity.getText()),fileName);  

                     
                                                                                                                        Dialog.show("Success","Evenement Modifier Successfully !", "OK", null);

                                                            new EventAfficherForm(res).show();

             }catch (NumberFormatException se) {
                Dialog.show("Echec","Verifier prix integer!", new Command("ok"));

                 } catch (IOException ex) {
                 }          
            }  });
            }
        }} 
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));

    }
}
