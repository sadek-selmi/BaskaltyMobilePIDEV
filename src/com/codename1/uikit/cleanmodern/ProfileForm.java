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
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.entities.User;
import com.esprit.services.UserService;
import java.util.ArrayList;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {
    
    ArrayList<User> finduserbyid = new ArrayList<>();

    public ProfileForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
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

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
           EncodedImage enco = EncodedImage.createFromImage(res.getImage("load.png"), false);
        String url = "http://localhost/Pi-dev-final/web/uploads/" + User.connectedUser.getImage();
        System.err.println( User.connectedUser.getImage());
        Image im = URLImage.createToStorage(enco, User.connectedUser.getImage(), url);
        ImageViewer imv = new ImageViewer(im);

        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                facebook,
                                FlowLayout.encloseCenter(
                                        imv),
                                twitter
                        )
                )
        ));
        
          System.out.println("*********************************************");
        System.out.println("id connected user :" +User.connectedUser.getId());
         System.out.println("*********************************************");
        
  
                TextField username = new TextField("");
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);
       username.setText(User.connectedUser.getUsername());

        TextField email = new TextField("", "E-Mail", 2, TextField.EMAILADDR);
      email.setText(User.connectedUser.getEmail());
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);

        TextField password = new TextField("", "Password", 2, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);
      password.setText(User.connectedUser.getPassword());

        TextField confirmpassword = new TextField("", "Confirm Password", 2, TextField.PASSWORD);
        confirmpassword.setUIID("TextFieldBlack");
        addStringValue("Confirm Password", confirmpassword);
        
       
      confirmpassword.setText(User.connectedUser.getPassword());

        Button Edit = new Button("Edit");
        addStringValue("", Edit);
        
        Edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                UserService us = new UserService();

                if (email.getText().equals("") || username.getText().equals("") || password.getText().equals("") || confirmpassword.getText().equals("")) 
                {
                    Dialog.show("Ooops", "There are fields missing", "OK", null);
                } else {

                    if (password.getText().equals(confirmpassword.getText())) {
                        us.editProfile(User.connectedUser.getId(), username.getText(), email.getText(), password.getText(), confirmpassword.getText());

                    } else {

                        Dialog.show("error", "Passwords don't match !", "try again", null);
                    }

                }
            }
        });
        
       

    
    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
