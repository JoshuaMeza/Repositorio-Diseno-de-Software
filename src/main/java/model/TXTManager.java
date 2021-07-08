package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class TXTManager implements FileManager {
    @Override
    public ArrayList<String> read(String fileName ) {
        ArrayList<String> output = new ArrayList<>();

        try {
            File file = new File( fileName + ".txt" );
            BufferedReader fl = new BufferedReader(new FileReader( file ));
            String row;

            while ( ( row = fl.readLine() ) != null ) {
                output.add(row);
            }

            fl.close();
        } catch ( Exception e ) {
            output = new ArrayList<>();
        }

        return output;
    }

    @Override
    public boolean write( String fileName, ArrayList<String> data, boolean append ) {
        boolean success = false;

        try {
            if ( data.size() > 0 ) {
                FileWriter fw = new FileWriter( new File( fileName + ".txt" ), append );

                for ( String line : data ) {
                    fw.write( line + "\n" );
                }

                fw.close();
                success = true;
            }
        } catch ( Exception e ) {
            success = false;
        }

        return success;
    }
}
