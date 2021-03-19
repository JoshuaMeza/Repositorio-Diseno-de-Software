package com.company.Model;

import java.util.ArrayList;

public class Model {
    private ArrayList<Student> students;

    public Model() {
        this.students = new ArrayList<>();
        read();
    }

    public void read() {
        ArrayList<String[]> info = new FileHandler().readStudents();

        for ( String[] row : info ) {
            if ( row[4].equals("null") ) {
                students.add( new Student(Integer.valueOf(row[0]), row[1], row[2], row[3]) );
            } else {
                students.add( new Student(Integer.valueOf(row[0]), row[1], row[2], row[3], Integer.valueOf(row[4])) );
            }
        }
    }

    public String getStudents() {
        String output = "";

        for ( Student student : students ) {
            output += student.toString() + "\n";
        }

        return output;
    }
}
