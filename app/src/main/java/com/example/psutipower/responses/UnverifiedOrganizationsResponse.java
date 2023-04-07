package com.example.psutipower.responses;

public class UnverifiedOrganizationsResponse {
    private final int id;
    private final String shortName;

    public UnverifiedOrganizationsResponse(int id, String shortName) {
        this.id = id;
        this.shortName = shortName;
    }

    public int getId() {
        return id;
    }

    public String getShortName() {
        return shortName;
    }
}
