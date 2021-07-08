package model;

import java.util.ArrayList;

public interface FileManager {
    public ArrayList<String> read( String fileName );
    public boolean write( String fileName, ArrayList<String> data, boolean append );
}
