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
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.entities.User;
import com.esprit.services.UserService;
import java.util.ArrayList;

/**
 * Sign in UI
 *
 * @author Shai Almog
 */
public class SignInForm extends BaseForm {

    ArrayList<User> users = new ArrayList<>();

    public SignInForm(Resources res) {
        super(new BorderLayout());
        setUIID("SignIn");
        getTitleArea().setUIID("Container");
       

        add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));

        TextField email = new TextField("", "Email", 20, TextField.EMAILADDR);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        email.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
   
        Button signUp = new Button("Sign Up");
        signUp.addActionListener(e -> new SignUpForm(res).show());
        signUp.setUIID("Link");
        Label doneHaveAnAccount = new Label("Don't have an account?");

        Container content = BoxLayout.encloseY(
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                signIn,
                FlowLayout.encloseCenter(doneHaveAnAccount, signUp)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signIn.requestFocus();
            
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (email.getText().equals("") || password.getText().equals("")) {
                    Dialog.show("error", "Champs Vides ", "ok", null);

                } else {

                    UserService us = new UserService();
                    users = us.login(email.getText(), password.getText());

                    for (int i = 0; i < users.size(); i++) {
                        System.out.println("********************* informatios users");
                        System.out.println(users.get(i).toString());
                        System.out.println("***************************************");
                    }

                    for (User u : users) {

                        User.connectedUser = u;

                        if (User.connectedUser.getEmail().equals(email.getText()) && User.connectedUser.getPassword().equals(password.getText()))
                        {
                            
                            if ( User.connectedUser.getEnabled().equals("0"))
                            {
                                 if (User.connectedUser.getRoles().contains("ROLE_MECHANICIEN")) 
                            {
                                ToastBar.showMessage("Authentification Succeeded !", FontImage.MATERIAL_CHECK);
                                new AssociationMainForm(res).show();
                            }
                            else if (User.connectedUser.getRoles().contains("ROLE_ASSOCIATION"))
                            {
                                  ToastBar.showMessage("Authentification Succeeded !", FontImage.MATERIAL_CHECK);
                                  new AssociationMainForm(res).show();
                            }
                            else if(User.connectedUser.getRoles().contains("ROLE_VENDEUR"))
                            {
                                  ToastBar.showMessage("Authentification Succeeded !", FontImage.MATERIAL_CHECK);
                                  new ProductsMainForm(res).show();
                            }
                           /* else 
                            {
                                  ToastBar.showMessage("Authentification Succeeded !", FontImage.MATERIAL_CHECK);
                                  new UserMainForm(res).show();
                            }*/
                            }
                            
                               else 
                            {   
                                  //ToastBar.showMessage(" ", FontImage.MATERIAL_CHECK);
                                 ToastBar.showMessage("Your account is not approved by the administrator !", FontImage.MATERIAL_CHECK);
                                 new UserMainForm(res).show();
                            }
                        }
                       /*if (u.getEmail().equals(email.getText()) && u.getPassword().equals(password.getText())) {
                            ToastBar.showMessage("Authentification Succeeded !", FontImage.MATERIAL_CHECK);
                            new NewsfeedForm(res).show();

                        }*/ else {
                            ToastBar.showMessage("Invalid Credentials !", FontImage.MATERIAL_ERROR);

                        }
                    }
                }
            }
        });

    }

}
