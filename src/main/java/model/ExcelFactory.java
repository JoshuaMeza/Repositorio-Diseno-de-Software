package model;

public class ExcelFactory implements Factory {
    @Override
    public FileManager createManager() {
        return new ExcelManager();
    }
}
