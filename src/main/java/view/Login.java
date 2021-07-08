package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Login extends JFrame implements GUI {
    private static final String INITIAL_VALUE = "";
    private JPanel background;
    private JTextField userBox;
    private JPasswordField passwordBox;
    private JButton button;
    private JLabel userLabel;
    private JLabel passwordLabel;

    public Login() {
        this.setVisible(false);
        this.add(background);
        this.setTitle("Iniciar Sesión");
        this.setSize(300, 140);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        reset();
    }

    public void reset() {
        userBox.setText( INITIAL_VALUE );
        passwordBox.setText( INITIAL_VALUE );
    }

    public void showError( String errMessage ) {
        JOptionPane.showMessageDialog( this, errMessage );
    }

    public String getUser() {
        return userBox.getText();
    }

    public String getPassword() {
        char[] password = passwordBox.getPassword();
        String output = "";

        for ( char o : password ) {
            output += o;
        }

        return output;
    }

    public void addContinueListener ( ActionListener acl ) {
        button.addActionListener( acl );
    }
}
