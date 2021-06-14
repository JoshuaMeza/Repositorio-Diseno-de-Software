package com.company.model;

import com.company.controller.SavingException;

import java.util.ArrayList;

public class Model {
    private ArrayList<Student> students;
    private ArrayList<User> users;

    public Model() {
        this.students = new ArrayList<>();
        this.users = new ArrayList<>();

        updateArraysFromFiles();
    }

    public void updateArraysFromFiles() {
        generateStudents();
        addGrades();
        generateUsers();
    }

    private void generateStudents() {
        ReadOnly fh = new FileHandler();
        ArrayList<String[]> info = fh.read( "students.csv" );

        for ( String[] row : info ) {
            try {
                students.add( new Student( Integer.parseInt( row[0] ), row[1], row[2], row[3] ) );
            } catch ( Exception e ) {
                System.out.println( e.toString() );
            }
        }
    }

    private void addGrades() {
        ReadOnly fh = new FileHandler();
        ArrayList<String[]> info = fh.read( "grades.csv" );

        for ( String[] row : info ) {
            try {
                int id = Integer.parseInt( row[0] ), grade = Integer.parseInt( row[2] );
                for ( Student student : students ) {
                    if ( id == student.getId() ) {
                        student.setGrade( grade );
                        break;
                    }
                }
            } catch ( Exception e ) {
                System.out.println( e.toString() );
            }
        }
    }

    private void generateUsers() {
        ReadOnly fh = new FileHandler();
        ArrayList<String[]> info = fh.read( "users.csv" );

        for ( String[] row : info ) {
            users.add( new User( row[0], row[1] ) );
        }
    }

    public String printStudents() {
        String output = "";

        for ( Student student : students ) {
            output += student.toString() + "\n";
        }

        return output.substring( 0, output.length()-1 );
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
                if ( student.getGrade() != -1 ) {
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

    private ArrayList<String> generateOutput( String subject, ArrayList arr ) {
        ArrayList<String> output = new ArrayList<>();

        for ( Object o : arr ) {
            if ( o instanceof OutputSubjectFile ) {
                OutputSubjectFile item = (OutputSubjectFile) o;
                String temp = item.produceOutput( subject );
                if ( temp != null ) {
                    output.add( temp );
                }
            } else {
                break;
            }
        }

        return output;
    }

    public void saveChanges() throws SavingException {
        WriteOnly fh = new FileHandler();
        ArrayList<String> output = generateOutput( "Diseno de Software", students );

        if ( ! fh.write( "grades.csv", output ) ) {
            throw new SavingException( "No fue posible realizar el guardado." );
        }
    }
}
