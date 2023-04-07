package com.example.psutipower.responses;

import android.widget.ListView;

import com.example.psutipower.requests.AddressDto;

import java.util.ArrayList;

public class OneUnverifiedOrganizationResponse {
    private int id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phoneNumber;
    private String username;
    private String email;
    private String organizationFullName;
    private String organizationShortName;
    private String inn;
    private String kpp;
    private String ogrn;
    private ArrayList<AddressForOneUnverifiedOrgResponse> address;

    public OneUnverifiedOrganizationResponse(int id, String firstName, String lastName,
                                             String patronymic, String phoneNumber, String username,
                                             String email, String organizationFullName, String organizationShortName,
                                             String inn, String kpp, String ogrn, ArrayList<AddressForOneUnverifiedOrgResponse> address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.email = email;
        this.organizationFullName = organizationFullName;
        this.organizationShortName = organizationShortName;
        this.inn = inn;
        this.kpp = kpp;
        this.ogrn = ogrn;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getOrganizationFullName() {
        return organizationFullName;
    }

    public String getOrganizationShortName() {
        return organizationShortName;
    }

    public String getInn() {
        return inn;
    }

    public String getKpp() {
        return kpp;
    }

    public String getOgrn() {
        return ogrn;
    }

    public ArrayList<AddressForOneUnverifiedOrgResponse> getAddress() {
        return address;
    }
}
