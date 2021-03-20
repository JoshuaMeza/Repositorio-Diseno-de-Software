package com.company.controller;

import com.company.model.Model;
import com.company.view.List;
import com.company.view.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private List list;
    private Model model;
    private Login login;

    public Controller(List list, Model model, Login login) {
        this.list = list;
        this.model = model;
        this.login = login;

        resetListOutput();

        login.addContinueListener( new PasswordChecker() );
    }

    public void resetListOutput() {
        list.setOutputBox( model.printStudents() );
    }

    class PasswordChecker implements ActionListener {
        @Override
        public void actionPerformed( ActionEvent e ) {
            try {
                if ( model.verifyLogin( login.getUser(), login.getPassword() ) ) {
                    login.setVisible(false);
                    list.setVisible(true);
                } else {
                    login.showError("Usuario o contraseña incorrecta.");
                }
            } catch ( Exception exception ) {
                login.showError(exception.toString());
            }
        }
    }
}
