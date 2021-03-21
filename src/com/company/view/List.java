package com.company.view;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class List extends JFrame implements GUI {
    private static final String INITIAL_VALUE = "";
    private JPanel background;
    private JTextArea outputBox;
    private JTextField gradeBox;
    private JButton registerButton;
    private JComboBox studentsSelection;
    private JLabel registerLabel;
    private JScrollPane pane;

    public List() {
        this.add(background);
        this.setTitle("Listado de alumnos");
        this.setSize(500, 230);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void reset( String output ) {
        outputBox.setText( output );
        gradeBox.setText( INITIAL_VALUE );
        studentsSelection.setSelectedIndex( 0 );
        changeAddButtonAccess( false );
    }

    public void showError( String errMessage ) {
        JOptionPane.showMessageDialog( this, errMessage );
    }

    public String getIdSelection() {
        return studentsSelection.getSelectedItem().toString();
    }

    public void updateSelectionEntries( ArrayList<String> ids ) {
        studentsSelection.removeAllItems();
        studentsSelection.addItem( "--- Matrículas ---" );

        for ( String id : ids ) {
            studentsSelection.addItem( id );
        }
    }

    public void setGradeBox( String grade ) {
        gradeBox.setText( grade );
    }

    public String getGradeBox() {
        return gradeBox.getText();
    }

    public void changeAddButtonAccess( boolean status ) {
        registerButton.setEnabled( status );
    }

    public void selectStudentListener( ItemListener sll ) {
        studentsSelection.addItemListener( sll );
    }

    public void updateGradesListener( ActionListener ugl ) {
        registerButton.addActionListener( ugl );
    }
}
