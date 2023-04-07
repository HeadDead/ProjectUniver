package com.example.psutipower.responses;

public class LoginResponse {
    private String token;
    private String type;
    private int id;
    private String username;
    private String[] role;

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String[] getRole() {
        return role;
    }
}
