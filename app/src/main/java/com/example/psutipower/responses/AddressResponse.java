package com.example.psutipower.responses;

import com.example.psutipower.requests.AddressTypeDto;

public class AddressResponse {
    private String subjectName;
    private String cityName;
    private String streetName;
    private String houseNumber;
    private String addInfo;
    private AddressTypeDto addressTypeDto;

    public String getSubjectName() {
        return subjectName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public AddressTypeDto getAddressTypeDto() {
        return addressTypeDto;
    }
}
