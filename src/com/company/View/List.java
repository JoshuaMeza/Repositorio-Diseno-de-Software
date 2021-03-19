package com.company.View;

import javax.swing.*;

public class List extends JFrame {
    private static final String INITIAL_VALUE = "";
    private JPanel background;
    private JTextArea outputBox;

    public List() {
        this.add(background);
        this.setTitle("Listado de alumnos");
        this.setSize(500, 230);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setOutputBox(INITIAL_VALUE);
    }

    public void setOutputBox( String output ) {
        outputBox.setText(output);
    }
}
