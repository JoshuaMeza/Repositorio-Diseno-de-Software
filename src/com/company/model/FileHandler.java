package com.company.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileHandler {

    public static ArrayList<String[]> read( String file ) {
        ArrayList<String[]> output = new ArrayList<>();

        try {
            File csv = new File( file );
            BufferedReader fl = new BufferedReader( new FileReader( csv ) );
            String row;

            while ( ( row = fl.readLine() ) != null ) {
                output.add( row.split( "," ) );
            }

            fl.close();
        } catch ( Exception e ) {
            output = new ArrayList<>();
            System.out.println("File not found");
        }

        return output;
    }

    public static boolean write( String file, ArrayList<String> data ) {
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
