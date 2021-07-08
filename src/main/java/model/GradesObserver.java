package model;

public interface GradesObserver {
    public boolean update( int id, String subject, int grade, Model model );
}
