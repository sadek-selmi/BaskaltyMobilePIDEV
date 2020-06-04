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

import com.codename1.components.FloatingHint;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.esprit.services.UserService;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {

    String fileName;
    Image img2;

    public SignUpForm(Resources res) {
        super(new BorderLayout());
        setUIID("SignIn");
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");

        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);
        final String[] jobPic = new String[1];
        Label jobIcon = new Label();
        Button image = new Button("Ajouter une image ");
        //addStringValue("image", image);

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

        ComboBox Role = new ComboBox("association", "mechanicien", "vendeur", "user");
     
        username.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);

        Button next = new Button("Sign Up");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> new SignInForm(res).show());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");

        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(confirmPassword),
                Role,
                image
        );

        //content.add(uploadim);
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        String sexe = "femme";
        next.addActionListener(e -> {

            UserService us = new UserService();

            if (email.getText().equals("") || username.getText().equals("") || password.getText().equals("") || confirmPassword.getText().equals("")) {
                Dialog.show("Ooops", "Looks like something is missing", "OK", null);
            } else {

                if (password.getText().equals(confirmPassword.getText())) {
                    us.Register(username.getText(), email.getText(), password.getText(), confirmPassword.getText(), Role.getSelectedItem().toString().toLowerCase(), res, fileName);
                    String pathToBeStored = "file://C:/wamp64/www/Pi-dev-final/web/uploads/" + fileName;
                    OutputStream os;
                    try {
                        os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored);
                        ImageIO.getImageIO().save(img2, os, ImageIO.FORMAT_PNG, 0.9f);
                        os.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                } else {

                    Dialog.show("error", "password doesn't match !", "try again", null);
                }

            }

        });
    }
   

}
