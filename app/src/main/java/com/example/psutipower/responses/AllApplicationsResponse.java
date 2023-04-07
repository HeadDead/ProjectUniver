package com.example.psutipower.responses;

public class AllApplicationsResponse {

    private final int id;
    private final String firstName;
    private final String organizationName;
    private final String price;
    private final AddressResponse address;

    public AllApplicationsResponse(int id, String firstName, String organizationName, AddressResponse address, String price) {
        this.id = id;
        this.firstName = firstName;
        this.organizationName = organizationName;
        this.address = address;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getPrice() {
        return price;
    }

    public AddressResponse getAddress() {
        return address;
    }
}
