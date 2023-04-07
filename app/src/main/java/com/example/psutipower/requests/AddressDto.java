package com.example.psutipower.requests;

public class AddressDto {
    private String subjectName;
    public String cityName;
    private String streetName;
    private String houseNumber;
    private String addInfo;
    private AddressTypeDto addressTypeDto;

    public AddressDto(String subjectName, String cityName, String streetName,
                      String houseNumber, String addInfo, AddressTypeDto addressTypeDto) {
        this.subjectName = subjectName;
        this.cityName = cityName;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.addInfo = addInfo;
        this.addressTypeDto = addressTypeDto;
    }
}
