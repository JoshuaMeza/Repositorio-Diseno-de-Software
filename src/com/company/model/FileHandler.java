package com.company.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileHandler {

    public ArrayList<String[]> readStudents() {
        ArrayList<String[]> output = new ArrayList<>();

        try {
            File csv = new File( "students.csv" );
            BufferedReader fl = new BufferedReader(new FileReader( csv ));
            String row;

            while ( ( row = fl.readLine() ) != null ) {
                String[] data = row.split(",");
                try {
                    String[] info = { data[0], data[1], data[2], data[3] };
                    output.add(info);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println( "Missing parameters on student!" );
                }
            }

            fl.close();
        } catch (Exception e) {
            output = new ArrayList<>();
            System.out.println("students.csv not found");
        }

        return output;
    }

    public ArrayList<String[]> readGrades() {
        ArrayList<String[]> output = new ArrayList<>();

        try {
            File csv = new File( "grades.csv" );
            BufferedReader fl = new BufferedReader(new FileReader( csv ));
            String row;

            while ( ( row = fl.readLine() ) != null ) {
                String[] data = row.split(",");
                try {
                    String[] info = { data[0], data[2] };
                    output.add(info);
                } catch ( IndexOutOfBoundsException e ) {
                    System.out.println( "Missing parameters on student!" );
                }

            }

            fl.close();
        } catch ( Exception e ) {
            output = new ArrayList<>();
            System.out.println( "grades.csv not found" );
        }

        return output;
    }

    public ArrayList<String[]> readUsers() {
        ArrayList<String[]> output = new ArrayList<>();

        try {
            File csv = new File( "users.csv" );
            BufferedReader fl = new BufferedReader(new FileReader( csv ));
            String row;

            while ( ( row = fl.readLine() ) != null ) {
                String[] data = row.split(",");
                try {
                    String[] info = { data[0], data[1] };
                    output.add(info);
                } catch ( Exception e ) {
                    System.out.println( "Missing parameters on user!" );
                }

            }

            fl.close();
        } catch (Exception e) {
            output = new ArrayList<>();
            System.out.println( "students.csv not found" );
        }

        return output;
    }

    public boolean writeFiles( String file, ArrayList<String> data ) {
        boolean success = true;

        try {
            if ( data.size() > 0 ) {
                File csv = new File( file );
                FileWriter fw = new FileWriter( csv );

                for ( String line : data ) {
                    fw.write( line + "\n" );
                }

                fw.close();
            }
        } catch ( Exception e ) {
            System.out.println( "Error while trying to write " + file );
            success = false;
        }

        return success;
    }
}
