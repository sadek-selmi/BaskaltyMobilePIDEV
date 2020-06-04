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
import com.codename1.io.MultipartRequest;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
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
import com.esprit.entities.User;
import com.esprit.entities.events;
import com.esprit.services.EventsService;
import com.esprit.services.ProductService;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;



/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class EventAjouterForm extends BaseForm {
String path;
String  fileName;
Container imgCtn;
Image img2 ;
ImageViewer l = new ImageViewer();
    ComboBox<String> autre;

MultipartRequest cr = new MultipartRequest();
    public EventAjouterForm(Resources res) {
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

        

      
        
        
        TextField Title = new TextField("");
       Title.setUIID("TextFieldBlack");
        addStringValue("Title", Title);
  //TextField adresse = new TextField("");
    //    adresse.setUIID("TextFieldBlack");
      //  addStringValue("adresse", adresse);
      autre = new ComboBox<String>();
        autre.addItem("Tunis");
        autre.addItem("Ariana");
        autre.addItem("Ben Arous");
        autre.addItem("Béja");
        autre.addItem("Bizerte");
        autre.addItem("Gabes");
        autre.addItem("Jandouba");
        autre.addItem("Gafsa");
        autre.addItem("Kairouan");
        autre.addItem("kasserine");
        autre.addItem("Kebili");
        autre.addItem("La manouba");
        autre.addItem("le kef");
        autre.addItem("Mahdia");
        autre.addItem("Médenine");
        autre.addItem("Monastir");
        autre.addItem("Nabeul");
        autre.addItem("Sfax");
        autre.addItem("Sidi Bouzid");
        autre.addItem("Siliana");
        autre.addItem("Sousse");
        autre.addItem("Tataouine");
        autre.addItem("Tozeur");
        autre.addItem("Zaghouan");
      
        autre.addItem("Autre");

              addStringValue("adresse", autre);

          TextField quantity = new TextField("");
        quantity.setUIID("TextFieldBlack");
        addStringValue("quantity", quantity);
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
                                                    //Image img2 = null;
                                                    try {
                                                        img2 = Image.createImage(FileSystemStorage.getInstance().openInputStream(filePath[0]));
                                                        //image_name[0] = System.currentTimeMillis() + ".png";
                                                        jobIcon.setIcon(img2);
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
        
        
        
        
        
        
        
        
        
        TextField prix = new TextField("");
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
 
         Button add = new Button("Ajouter");

       addStringValue("", add);

          add.addActionListener((ActionEvent e)-> {
             EventsService ps = new EventsService();
    //public events(int Id_User, String Titre, String Contenu, String location, String image, Date debut, Date fin, int prix, int quantity, theme theme) {
        if (Title.getText().equals("")) {
                Dialog.show("champs vides", "Titre Manquant ", "ok", null);

            } else if (Description.getText().equals("")) {
                Dialog.show("champs vides", "Description Manquant ", "ok", null);

            } else if (prix.getText().equals("")) {
                Dialog.show("champs vides", "prix Manquant ", "ok", null);

            } else if (quantity.getText().equals("")) {
                Dialog.show("Champs vides", "quantity Manquant ", "ok", null);

            }else if (fileName.equals("")) {
                Dialog.show("Champs vides", "Image Manquant ", "ok", null);

            }
            
            
            
            else {
            
                 try {
                                 ps.addEvent(new events(User.connectedUser.getId(), Title.getText(), Description.getText(),autre.getSelectedItem(),Debut.getText(),fileName, end.getText(), Integer.parseInt(prix.getText()), Integer.parseInt(quantity.getText())));  

                       String pathToBeStored = "file://C:/wamp64/www/Pi-dev-final/web/uploads/" + fileName;
                                                            OutputStream os;
                     os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                     ImageIO.getImageIO().save(img2, os, ImageIO.FORMAT_PNG, 0.9f);
                                                            os.close();
              
            
                                                            Dialog.show("Success","Evenement Added Successfully !", "OK", null);
                                                            new EventAfficherForm(res).show();
               
                 }catch (NumberFormatException se) {
                Dialog.show("Echec","Verifier prix or quantity integer!", new Command("ok"));

                 } catch (IOException ex) {
                 }
                                                                
               

                      
            }  });
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));

    }
}
