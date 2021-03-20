package com.company.model;

public class User {
    private String user;
    private String password;

    public User( String user, String password ) {
        this.user = user;
        this.password = password;
    }

    public boolean verifyUser( String user, String password ) {
        return this.user.equals(user) && this.password.equals(password);
    }
}
