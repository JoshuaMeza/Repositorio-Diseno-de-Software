package com.company.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
                String[] info = { data[0], data[1], data[2], data[3], data[4] };
                output.add(info);
            }

            fl.close();
        } catch (FileNotFoundException e) {
            output = new ArrayList<>();
        } catch (IOException e) {
            output = new ArrayList<>();
        }

        return output;
    }
}
