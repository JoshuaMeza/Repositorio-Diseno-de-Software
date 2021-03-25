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
        FileHandler fh = new FileHandler();
        ArrayList<String[]> info = fh.readStudents();

        // Generate Students
        for ( String[] row : info ) {
            try {
                students.add( new Student( Integer.valueOf(row[0]), row[1], row[2], row[3] ) );
            } catch ( Exception e ) {
                System.out.println(e.toString());
            }
        }

        // Set grades
        info = fh.readGrades();

        if ( info.size() > 0 ) {
            for ( Student student : students ) {
                int i = 0;
                boolean found = false;

                try {
                    for ( String[] row : info ) {
                        if ( Integer.valueOf( row[0] ) == student.getId() ) {
                            student.setGrade( Integer.valueOf( row[1] ) );
                            found = true;
                            break;
                        }

                        i++;
                    }
                } catch ( Exception e ) {
                    break;
                }

                if ( found ) {
                    info.remove( i );
                }
            }
        }

        // Generate Users
        info = fh.readUsers();

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

    public void saveChanges() throws SavingException {
        ArrayList<String> output = new ArrayList<>();

        for ( Student student : students ) {
            int grade = student.getGrade();

            if ( grade != -1 ) {
                output.add( student.getId() + ",Diseño de Software," + grade );
            }
        }

        if ( ! new FileHandler().writeFiles( "grades.csv", output ) ) {
            throw new SavingException( "No fue posible realizar el guardado." );
        }
    }
}
