package com.company.model;

public class Student implements OutputSubjectFile {
    private int id;
    private String firstLastName;
    private String secondLastName;
    private String name;
    private int grade;

    public Student( int id, String firstLastName, String secondLastName, String name, int grade ) {
        this.id = id;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.name = name;
        this.grade = grade;
    }

    public Student( int id, String firstLastName, String secondLastName, String name ) {
        this( id, firstLastName, secondLastName, name, -1 );
    }

    public int getId() {
        return id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String produceOutput(String subject) {
        return getGrade() != -1 ? getId() + "," + subject + "," + getGrade() : null ;
    }

    @Override
    public String toString() {
        String output;

        if ( grade != -1 ) {
            output = firstLastName + " " + secondLastName + " " + name + " (" + id + "): " + grade;
        } else {
            output = firstLastName + " " + secondLastName + " " + name + " (" + id + "): _";
        }

        return output;
    }
}
