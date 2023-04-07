package com.example.psutipower.responses;

public class ApplicationsFromUsersResponse {
    private final String firstName;
    private final String username;
    private final String phoneNumber;

    public ApplicationsFromUsersResponse(String firstName, String username, String phoneNumber) {
        this.firstName = firstName;
        this.username = username;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
