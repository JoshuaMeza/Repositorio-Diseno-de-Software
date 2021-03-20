package com.company.model;

import java.util.ArrayList;

public class Model {
    private ArrayList<Student> students;
    private ArrayList<User> users;

    public Model() {
        this.students = new ArrayList<>();
        this.users = new ArrayList<>();

        read();
    }

    public void read() {
        ArrayList<String[]> info = new FileHandler().readStudents();

        for ( String[] row : info ) {
            try {
                if ( row[4].equals("null") ) {
                    students.add( new Student(Integer.valueOf(row[0]), row[1], row[2], row[3]) );
                } else {
                    students.add( new Student(Integer.valueOf(row[0]), row[1], row[2], row[3], Integer.valueOf(row[4])) );
                }
            } catch ( Exception e ) {}
        }

        info = new FileHandler().readUsers();

        for ( String[] row : info ) {
            users.add( new User( row[0], row[1] ) );
        }
    }

    public String printStudents() {
        String output = "";

        for ( Student student : students ) {
            output += student.toString() + "\n";
        }

        return output;
    }

    public boolean verifyLogin( String username, String password ) {
        boolean result = false;

        for ( User user : users ) {
            if ( user.verifyUser( username, password ) ) {
                result = true;
                break;
            }
        }

        return result;
    }
}
