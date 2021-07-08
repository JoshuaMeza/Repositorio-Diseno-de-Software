package controller;

import model.Model;
import view.List;
import view.Login;

import java.awt.event.*;

public class Controller {
    private List list;
    private Model model;
    private Login login;
    private State state;

    public Controller(List list, Model model, Login login) {
        this.list = list;
        this.model = model;
        this.login = login;
        this.state = new NotGraded();
    }

    public List getList() {
        return list;
    }

    public Model getModel() {
        return model;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void start() {
        // Update view
        list.updateSelectionEntries( model.getIds() );
        list.setOutputText( model.printStudents() );

        // Add listeners
        list.selectStudentListener( new StudentChangeListener() );
        list.updateGradesListener( new UpdateGradesListener() );
        list.saveGradesListener( new SaveChangesListener() );
        login.addContinueListener( new PasswordCheckerListener() );

        // State action
        state.action( this );

        // Show view
        login.setVisible(true);
    }

    class PasswordCheckerListener implements ActionListener {
        @Override
        public void actionPerformed( ActionEvent e ) {
            try {
                if ( model.verifyLogin( login.getUser(), login.getPassword() ) ) {
                    login.setVisible(false);
                    list.setVisible(true);
                } else {
                    throw new Exception();
                }
            } catch ( Exception exception ) {
                login.reset();
                login.showError( "Usuario o contraseña incorrecta." );
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
                        list.setGradeBox( model.searchGrade( Integer.parseInt( event.getItem().toString() ) ) );
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
                double grade = Double.parseDouble( list.getGradeBox() );

                if ( grade >= 1 && grade <= 100 && grade % 1 == 0 ) {
                    model.setGrade( Integer.parseInt( list.getIdSelection() ), Integer.parseInt( list.getGradeBox() ) );
                } else {
                    throw new NumberFormatException();
                }
            } catch ( NumberFormatException exception ) {
                list.showError("Debes introducir un número entero entre el 1 y el 100.");
            } catch ( SavingException exception ) {
                list.showError( "Surgió un problema con el guardado." );
            } catch ( Exception exception ) {
                list.showError( exception.toString() );
            } finally {
                list.reset();
                list.setOutputText( model.printStudents() );
                state.action( Controller.this );
            }
        }
    }

    class SaveChangesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                model.saveChanges();
            } catch ( SavingException exception ) {
                list.showError( "No fue posible guardar las calificaciones." );
            }
        }
    }
}
