package com.fbs.admin.authentication;

import java.io.Serializable;

public class AuthRequest implements Serializable {
    private String username;
    private String password;

    //need default constructor for JSON Parsing
    public AuthRequest() {

    }

    public AuthRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
