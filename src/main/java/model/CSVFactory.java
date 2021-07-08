package model;

public class CSVFactory implements Factory {
    @Override
    public FileManager createManager() {
        return new CSVManager();
    }
}
