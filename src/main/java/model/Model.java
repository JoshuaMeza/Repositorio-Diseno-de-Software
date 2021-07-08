package model;

import controller.SavingException;

import java.util.ArrayList;

public class Model {
    private static Model model;
    private final String KEY = "DS-Project";
    private ArrayList<Student> students;
    private ArrayList<User> users;
    private Factory[] factories;
    private GradesObserver gradesObserver;

    private Model() {
        this.students = new ArrayList<>();
        this.users = new ArrayList<>();
        this.factories = new Factory[]{ new TXTFactory(), new CSVFactory(), new ExcelFactory() };
        this.gradesObserver = new GradeUpdater();

        updateArraysFromFiles();
    }

    public static Model getInstance() {
        if ( model == null ) model = new Model();
        return model;
    }

    public Factory[] getFactories() {
        return factories;
    }

    public void updateArraysFromFiles() {
        generateUsers();
        generateStudents();
        addGrades();
    }

    private void generateUsers() {
        ArrayList<String> info = factories[0].createManager().read( "users" );

        for ( String line : info ) {
            String[] row = line.split(",");
            if ( row.length == 2 ) {
                users.add( new User( AES.encrypt( row[0], KEY ), AES.encrypt( row[1], KEY ) ) );
            }
        }
    }

    private void generateStudents() {
        ArrayList<String> info = factories[1].createManager().read( "students" );

        for ( String line : info ) {
            String[] row = line.split(",");
            try {
                if ( row.length == 4 ) {
                    students.add( new Student( Integer.parseInt( row[0] ), row[1], row[2], row[3] ) );
                }
            } catch ( Exception e ) {
                continue;
            }
        }
    }

    private void addGrades() {
        ArrayList<String> info = factories[2].createManager().read( "grades" );

        for ( String line : info ) {
            String[] row = line.split(",");
            try {
                if ( row.length == 3 ) {
                    int id = Integer.parseInt( row[0] ), grade = Integer.parseInt( row[2] );
                    for ( Student student : students ) {
                        if ( id == student.getId() ) {
                            student.setGrade( grade );
                            break;
                        }
                    }
                }
            } catch ( Exception e ) {
                continue;
            }
        }
    }

    public String printStudents() {
        String output = "";

        for ( Student student : students ) {
            output += student.toString() + "\n";
        }

        return output.length() > 0 ? output.substring( 0, output.length()-1 ) : "";
    }

    public boolean verifyLogin( String username, String password ) {
        boolean result = false;
        username = AES.encrypt( username, KEY );
        password = AES.encrypt( password, KEY );

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

    public void setGrade( int id, int grade ) throws SavingException {
        for ( Student student : students ) {
            if ( student.getId() == id ) {
                if ( !gradesObserver.update( id, "Diseño de Software", grade, this ) ) {
                    throw new SavingException("No fue posible realizar el guardado.");
                }
                student.setGrade( grade );
                break;
            }
        }
    }

    public int getStudentsAmount() {
        return students.size();
    }

    public int getGradesAmount() {
        int result = 0;
        for ( Student student : students ) {
            if ( student.getGrade() != -1 ) result++;
        }
        return result;
    }

    public void saveChanges() throws SavingException {
        ArrayList<String> output = new ArrayList<>();

        for ( Student student : students ) {
            int grade = student.getGrade();

            if ( grade != -1 ) {
                output.add( student.getId() + ",Diseño de Software," + grade );
            }
        }

        if ( ! factories[2].createManager().write( "grades", output, false ) ) {
            throw new SavingException( "No fue posible realizar el guardado." );
        }
    }
}
