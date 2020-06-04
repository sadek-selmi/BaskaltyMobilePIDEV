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
import com.esprit.entities.Participer;
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
public class EventAfficherForm extends BaseForm {

    int id_product;
    int s = 0;
    String dds;
    String intr;
    EventsService ps = new EventsService();

    ArrayList<events> Events = ps.getAllEvents();
    ArrayList<comment> com = ps.getAllcommenter();

    private static EventAfficherForm instance;

    public static EventAfficherForm getInstance() {
        return instance;
    }

    public EventAfficherForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Evenement");
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

        setLayout(BoxLayout.y());
        for (events p : Events) {
            add(AddItems(p, res, p.getId_User()));
        }
    }

    public Container AddItems(events pro, Resources theme, int id) {
        Container item = new Container(new BoxLayout(BoxLayout.X_AXIS_NO_GROW));

        EncodedImage enco = EncodedImage.createFromImage(theme.getImage("load.png"), false);
        String url = "http://localhost/Pi-dev-final/web/uploads/" + pro.getImage();
        Image im = URLImage.createToStorage(enco, pro.getImage(), url);
        ImageViewer imv = new ImageViewer(im);
        item.add(imv);
        imv.addPointerReleasedListener((evt) -> {
            EventAfficherDetailsForm h = new EventAfficherDetailsForm(theme, pro.getId_User());
            h.show();

        });

        Container data = new Container(BoxLayout.y());

        Label tfname = new Label(pro.getTitre());
        tfname.setUIID("TextHinte");

        Container nom = new Container(BoxLayout.x());
        nom.add(tfname);

        data.add(nom);

        Label lreference = new Label("Adresse :");
        Font fnt2 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        lreference.getUnselectedStyle().setFont(fnt2);
        SpanLabel tfreference = new SpanLabel(pro.getLocation());

        Container reference = new Container(BoxLayout.x());
        reference.add(lreference);
        reference.add(tfreference);

        data.add(reference);

        Label lprix = new Label("Prix :");
        Font fnt4 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        lprix.getUnselectedStyle().setFont(fnt4);
        Label tfprix = new Label(Float.toString(pro.getPrix()) + "DT");

        Container prix = new Container(BoxLayout.x());
        prix.add(lprix);
        prix.add(tfprix);

        data.add(prix);

        Label lquantity = new Label("Disponible :");
        Font fnt3 = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL);
        lquantity.getUnselectedStyle().setFont(fnt3);
        SpanLabel tfquantity = new SpanLabel(Integer.toString(pro.getQuantity()));

        Container quantity = new Container(BoxLayout.x());
        quantity.add(lquantity);
        quantity.add(tfquantity);
        data.add(quantity);

        Container pepe = new Container(BoxLayout.x());

        Participer p = new Participer(User.connectedUser.getId(), pro.getId_User());

        Button participer = new Button(FontImage.MATERIAL_CHECK_BOX);
        if (pro.getQuantity()== 0) {
            participer.setEnabled(false);
            participer.setText("FULL");
            participer.setUIID("TextFULL");
        } 
        participer.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               if (ps.findparticipationC(p)) {
        
                                   Dialog.show("Success", "Vouz etez participier!", "OK", null);

               
               
               } else {
          System.out.println("Participer");

                    System.out.println(p.toString());
                    EventsService pmodifs = new EventsService();

                    pmodifs.Participer(pro.getId_User(), User.connectedUser.getId());
                    Dialog.show("Success", "voir  mail pour confirmation!", "OK", null);

                    ConfirmationForm h = new ConfirmationForm(theme, pro.getId_User());
                    h.show();
        }
                

            }

        });

        Button intersted = new Button(FontImage.MATERIAL_STAR);

        intersted.addPointerPressedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                EventsService pmodifs = new EventsService();

                pmodifs.Intersted(pro.getId_User(), User.connectedUser.getId());
                Dialog.show("Success", "Intersted Successfully !", "OK", null);
                EventAfficherForm h = new EventAfficherForm(theme);
                h.show();
            }

        });

        intr = Integer.toString(pro.getInterstednumber());

        dds = Integer.toString(pro.getParticipernumber());

        pepe.add(intr);
        pepe.add(intersted);
        pepe.add(dds);

        pepe.add(participer);

        data.add(pepe);

        item.add(data);
        return item;
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));

    }

}
