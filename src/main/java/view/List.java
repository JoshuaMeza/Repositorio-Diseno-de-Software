package view;

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
    private JLabel saveLabel;
    private JButton saveButton;
    private JProgressBar gradesProgress;
    private JLabel stateLabel;

    public List() {
        this.setVisible(false);
        this.add(background);
        this.setTitle("Listado de alumnos");
        this.setSize(500, 320);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        reset();
    }

    public void reset() {
        outputBox.setText( INITIAL_VALUE );
        gradeBox.setText( INITIAL_VALUE );
        studentsSelection.setSelectedIndex( 0 );
        changeAddButtonAccess( false );
        changeSaveButtonAccess( false );
        updateStateLabel( "Estado" );
        updateProgressBar( 0, 100 );
    }

    public void setOutputText( String info ) {
        outputBox.setText( info );
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
        for ( String id : ids ) studentsSelection.addItem( id );
        studentsSelection.setSelectedIndex( 0 );
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

    public void changeSaveButtonAccess( boolean status ) {
        saveButton.setEnabled( status );
    }

    public void updateStateLabel( String state ) {
        stateLabel.setText( state );
    }

    public void updateProgressBar( int progress, int max ) {
        gradesProgress.setMaximum( max );
        gradesProgress.setValue( progress );
    }

    public void selectStudentListener( ItemListener sll ) {
        studentsSelection.addItemListener( sll );
    }

    public void updateGradesListener( ActionListener ugl ) {
        registerButton.addActionListener( ugl );
    }

    public void saveGradesListener( ActionListener sgl ) {
        saveButton.addActionListener( sgl );
    }
}
