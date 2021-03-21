package com.company.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileHandler {

    public ArrayList<String[]> readStudents() {
        ArrayList<String[]> output = new ArrayList<>();

        try {
            BufferedReader fl = new BufferedReader(new FileReader(Paths.get("students.csv").toString()));
            String row;

            while ( ( row = fl.readLine() ) != null ) {
                String[] data = row.split(",");
                try {
                    String[] info = { data[0], data[1], data[2], data[3], data[4] };
                    output.add(info);
                } catch (IndexOutOfBoundsException e) {}
            }

            fl.close();
        } catch (FileNotFoundException e) {
            output = new ArrayList<>();
        } catch (IOException e) {
            output = new ArrayList<>();
        }

        return output;
    }

    public ArrayList<String[]> readUsers() {
        ArrayList<String[]> output = new ArrayList<>();

        try {
            BufferedReader fl = new BufferedReader(new FileReader(Paths.get("users.csv").toString()));
            String row;

            while ( ( row = fl.readLine() ) != null ) {
                String[] data = row.split(",");
                try {
                    String[] info = { data[0], data[1] };
                    output.add(info);
                } catch (IndexOutOfBoundsException e) {}
            }

            fl.close();
        } catch (FileNotFoundException e) {
            output = new ArrayList<>();
        } catch (IOException e) {
            output = new ArrayList<>();
        }

        return output;
    }

    public boolean writeStudents( String file, ArrayList<String> data ) {
        boolean success = true;

        try {
            FileWriter fw = new FileWriter( Paths.get( file ).toString() );

            for ( String line : data ) {
                fw.write( line + "\n" );
            }

            fw.close();
        } catch ( IOException e ) {
            success = false;
        }

        return success;
    }
}
