package com.example.psutipower.requests;

public class OrganizationRequest {
    String firstName;
    String lastName;
    String patronymic;
    String username;
    String password;
    String phoneNumber;
    String email;
    String organizationFullName;
    String organizationShortName;
    String inn;
    String kpp;
    String ogrn;
    String role;
    AddressDto addressDto;

    public OrganizationRequest(String firstName, String lastName, String patronymic,
                               String username, String password, String phoneNumber, String email,
                               String role, String organizationFullName, String organizationShortName,
                               String inn, String kpp, String ogrn, AddressDto addressDto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.organizationFullName = organizationFullName;
        this.organizationShortName = organizationShortName;
        this.inn = inn;
        this.kpp = kpp;
        this.ogrn = ogrn;
        this.addressDto = addressDto;
        this.role = role;
    }
}
