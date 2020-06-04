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
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.Stroke;
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
import com.codename1.ui.plaf.CSSBorder;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Product;
import com.esprit.entities.User;
import com.esprit.entities.events;
import com.esprit.entities.comment;

import com.esprit.services.EventsService;
import com.esprit.services.PanierService;
import com.esprit.services.ProductService;
import java.util.ArrayList;
import java.util.Date;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class EventAfficherDetailsForm extends BaseForm {

    int id_product;
    int s = 0;
 private ImageViewer photoProfilViewer;
    private Image imgss;
    EventsService ps = new EventsService();

    ArrayList<events> Events = ps.getAllEvents();
    ArrayList<comment> com = ps.getAllcommenter();

    private static EventAfficherDetailsForm instance;

    public static EventAfficherDetailsForm getInstance() {
        return instance;
    }

    public EventAfficherDetailsForm(Resources res, int id ) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Evenement");
        getContentPane().setScrollVisible(false);
     for (events p : Events) {
            if(p.getId_User()==id){
      
                
                
                
                
                super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });
 EncodedImage enco = EncodedImage.createFromImage(res.getImage("load.png"), false);
        String url = "http://localhost/Pi-dev-final/web/uploads/" + p.getImage();
       
        Image img = URLImage.createToStorage(enco, p.getImage(), url);
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

        setLayout(BoxLayout.y());
   
            
             add(AddItems(p, res,id));
            }
           
        }
    }

    public Container AddItems(events pro, Resources theme,int id ) {
        Container item = new Container(new BoxLayout(BoxLayout.X_AXIS_NO_GROW));

        

        Container data = new Container(BoxLayout.yCenter());

        Label tfname = new Label(pro.getTitre());
     
    tfname.setUIID("Textsof");
        Container nom = new Container(BoxLayout.xCenter());

        nom.add(tfname);

        data.add(nom);

        Label lreference = new Label("Adresse :");
       
    lreference.setUIID("DialogBody");
    lreference.getAllStyles().setFgColor(0);
        SpanLabel tfreference = new SpanLabel(pro.getContenu());

        Container reference = new Container(BoxLayout.x());
        reference.add(lreference);
        reference.add(tfreference);

        data.add(reference);

        Label lprix = new Label("Prix :");
     lprix.setUIID("DialogBody");
    lprix.getAllStyles().setFgColor(0);
        Label tfprix = new Label(Float.toString(pro.getPrix()) + "DT");

        Container prix = new Container(BoxLayout.x());
        prix.add(lprix);
        prix.add(tfprix);

        data.add(prix);

        Label lquantity = new Label("ticket");
         lquantity.setUIID("DialogBody");
    lquantity.getAllStyles().setFgColor(0);
        Font fnt3 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        lquantity.getUnselectedStyle().setFont(fnt3);
        SpanLabel tfquantity = new SpanLabel(Integer.toString(pro.getQuantity())+"Disponible");

        Container quantity = new Container(BoxLayout.x());
        quantity.add(lquantity);
        quantity.add(tfquantity);
      
        Label ss = new Label("Description :");
         ss.setUIID("DialogBody");
    ss.getAllStyles().setFgColor(0);
        Font fntdd3 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        ss.getUnselectedStyle().setFont(fntdd3);
        SpanLabel ssc = new SpanLabel(pro.getContenu());

        Container quantddity = new Container(BoxLayout.x());
        quantddity.add(ss);
        quantddity.add(ssc);
        
        
        data.add(quantddity);
       
                Label cooo = new Label("Liste Commentaire");
     
    cooo.setUIID("Textsofz");
         data.add(cooo);


            for (comment c : com) {
                if (id == c.getId()) {

               Container commentContainer=new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
                    //color(0x99CCCC);
           
            Container authorDateContainer=new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
            
            EncodedImage enco = EncodedImage.createFromImage(theme.getImage("load.png"), false);
            String url="http://localhost/Pi-dev-final/web/uploads/" + c.getImageFile();
            imgss=URLImage.createToStorage(enco.scaledEncoded(150,150), url,url, URLImage.RESIZE_SCALE);
            photoProfilViewer=new ImageViewer(imgss);
            Container photoContainer=new Container(BoxLayout.x());
            photoContainer.add(photoProfilViewer);
            authorDateContainer.add(BorderLayout.WEST,photoContainer);
            
            Label auteurLabel=new Label();
            auteurLabel.setText(c.getUsername());
              auteurLabel.getAllStyles().setFgColor(0xE06666);

            Container auteurContainer=new Container(BoxLayout.x());
            auteurContainer.add(auteurLabel);
            authorDateContainer.add(BorderLayout.NORTH,auteurContainer);
            
   
            SpanLabel avisLabel=new SpanLabel();
            avisLabel.setText(c.getContent());

            Container avisContainer= new Container(BoxLayout.x());
            avisContainer.add(avisLabel);
            
            commentContainer.addComponent(BorderLayout.WEST,authorDateContainer);
//            avisContainer.setX(1);
            commentContainer.addComponent(BorderLayout.CENTER,avisContainer);
          // commentContainer.getAllStyles().setBorder(            CSSBorder.createDashedBorder(0x99CCCC)


             data.add(commentContainer)  ;     
                    
                    
                    
                    
                    
                    
                    
                    
                
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            s++;
            }}
            if (s == 0) {

                    Label comme = new Label("il ne pas de Commentaire");
   Font fnt3com = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        comme.getUnselectedStyle().setFont(fnt3);

        Container cv = new Container(BoxLayout.x());
        cv.add(comme);
        data.add(cv);
            }

            TextArea ct = new TextArea();
            
            ct.setHint("insert commenter");
                    Container cdd = new Container(BoxLayout.xCenter());

            Button ajoutercom = new Button("Insert");
           
            ajoutercom.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    comment c = new comment(ct.getText(), id,User.connectedUser.getId());
                    ps.addCommenter(c);
                    Dialog.show("Success", "votre Commentaire ", "ok", null);
                                       EventAfficherDetailsForm h = new EventAfficherDetailsForm(theme,pro.getId_User());

                    h.show();
                }
            });
           
            

            Button btnAnnuler = new Button("Retour");
            btnAnnuler.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    EventAfficherForm h = new EventAfficherForm(theme);
                    h.show();
                }
            });

            data.add(ct);
            cdd.add(ajoutercom);
            cdd.add(btnAnnuler);
     data.add(cdd);
        
        
        
        
        
        
        
        
        

        item.add(data);
        return item;
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xE06666));

    }

}
