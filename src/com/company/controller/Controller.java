package com.company.controller;

import com.company.model.Model;
import com.company.view.List;
import com.company.view.Login;

import java.awt.event.*;

public class Controller {
    private List list;
    private Model model;
    private Login login;

    public Controller(List list, Model model, Login login) {
        this.list = list;
        this.model = model;
        this.login = login;

        list.updateSelectionEntries( model.getIds() );
        list.reset( model.printStudents() );

        list.selectStudentListener( new StudentChangeListener() );
        list.updateGradesListener( new UpdateGradesListener() );
        list.addWindowListener( new WindowClosingListener() );

        login.addContinueListener( new PasswordChecker() );
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
                login.showError( exception.toString() );
            }
        }
    }

    class StudentChangeListener implements ItemListener {
        @Override
        public void itemStateChanged( ItemEvent event ) {
            if ( event.getStateChange() == ItemEvent.SELECTED ) {
                try {
                    if ( !event.getItem().toString().equals("--- Matrículas ---") ) {
                        list.changeAddButtonAccess( true );
                        list.setGradeBox( model.searchGrade( Integer.valueOf( event.getItem().toString() ) ) );
                    } else {
                        list.changeAddButtonAccess( false );
                    }
                } catch ( Exception exception ) {
                    list.showError( exception.toString() );
                }
            }
        }
    }

    class UpdateGradesListener implements ActionListener {
        @Override
        public void actionPerformed( ActionEvent e ) {
            try {
                double grade = Double.valueOf( list.getGradeBox() );

                if ( grade >= 1 && grade <= 100 && grade % 1 == 0 ) {
                    model.setGrade( Integer.valueOf( list.getIdSelection() ), Integer.valueOf( list.getGradeBox() ) );
                    list.reset( model.printStudents() );
                } else {
                    throw new NumberFormatException();
                }
            } catch ( NumberFormatException exception ) {
                list.showError( "Debes introducir un número entero entre el 1 y el 100." );
            } catch ( Exception exception ) {
                list.showError( exception.toString() );
            }
        }
    }

    class WindowClosingListener extends WindowAdapter {
        @Override
        public void windowClosing( WindowEvent windowEvent ) {
            try {
                model.saveChanges();
            } catch (SavingException exception) {
                list.showError( exception.toString() );
            }
        }
    }
}
