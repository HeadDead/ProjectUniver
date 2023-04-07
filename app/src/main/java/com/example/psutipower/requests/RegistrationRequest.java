package com.example.psutipower.requests;

public class RegistrationRequest {
    String firstName;
    String lastName;
    String patronymic;
    String username;
    String password;
    String phoneNumber;
    String email;
    String role;

    public RegistrationRequest(String firstName, String lastName, String patronymic,
                               String username, String password, String phoneNumber, String email,
                               String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
    }
}
