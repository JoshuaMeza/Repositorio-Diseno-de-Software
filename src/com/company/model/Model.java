package com.company.model;

import com.company.controller.SavingException;

import java.util.ArrayList;

public class Model {
    private ArrayList<Student> students;
    private ArrayList<User> users;

    public Model() {
        this.students = new ArrayList<>();
        this.users = new ArrayList<>();

        read();
    }

    public void read() {
        ArrayList<String[]> info = new FileHandler().readStudents();

        for ( String[] row : info ) {
            try {
                if ( row[4].equals("null") ) {
                    students.add( new Student(Integer.valueOf(row[0]), row[1], row[2], row[3]) );
                } else {
                    students.add( new Student(Integer.valueOf(row[0]), row[1], row[2], row[3], Integer.valueOf(row[4])) );
                }
            } catch ( Exception e ) {}
        }

        info = new FileHandler().readUsers();

        for ( String[] row : info ) {
            users.add( new User( row[0], row[1] ) );
        }
    }

    public String printStudents() {
        String output = "";

        for ( Student student : students ) {
            output += student.toString() + "\n";
        }

        return output;
    }

    public boolean verifyLogin( String username, String password ) {
        boolean result = false;

        for ( User user : users ) {
            if ( user.verifyUser( username, password ) ) {
                result = true;
                break;
            }
        }

        return result;
    }

    public ArrayList<String> getIds() {
        ArrayList<String> ids = new ArrayList<>();

        for ( Student student : students ) {
            ids.add( "" + student.getId() );
        }

        return ids;
    }

    public String searchGrade( int id ) {
        String grade = "";

        for ( Student student : students ) {
            if ( student.getId() == id ) {
                if ( Integer.valueOf( student.getGrade() ) != -1 ) {
                    grade += student.getGrade();
                }
                break;
            }
        }

        return grade;
    }

    public void setGrade( int id, int grade ) {
        for ( Student student : students ) {
            if ( student.getId() == id ) {
                student.setGrade( grade );
                break;
            }
        }
    }

    public boolean verifyGrades() {
        boolean status = true;

        for ( Student student : students ) {
            if ( Integer.valueOf( student.getGrade() ) == -1 ) {
                status = false;
                break;
            }
        }

        return status;
    }

    public void generateFinalCSV() throws SavingException {
        ArrayList<String> output = new ArrayList<>();

        for ( Student student : students ) {
            output.add( student.getId() + ",Diseño de Software," + student.getGrade() );
        }

        if ( ! new FileHandler().writeStudents( "calificaciones.csv", output ) ) {
            throw new SavingException( "No fue posible generar el archivo." );
        }
    }

    public void saveChanges() throws SavingException {
        ArrayList<String> info = new ArrayList<>();

        for ( Student student : students ) {
            info.add( student.savingFormat() );
        }

        if ( ! new FileHandler().writeStudents( "students.csv" ,info ) ) {
            throw new SavingException("No fue posible guardar los cambios.");
        }
    }
}
