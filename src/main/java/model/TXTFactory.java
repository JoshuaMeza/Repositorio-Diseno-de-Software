package model;

public class TXTFactory implements Factory {
    @Override
    public FileManager createManager() {
        return new TXTManager();
    }
}
