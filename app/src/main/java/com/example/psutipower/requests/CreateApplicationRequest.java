package com.example.psutipower.requests;

public class CreateApplicationRequest {
    String firstName;
    String dateApplication;
    String addInfo;
    ServiceDopDto serviceDopDto;

    public CreateApplicationRequest(String firstName, String dateApplication, String addInfo, ServiceDopDto serviceDopDto) {
        this.firstName = firstName;
        this.dateApplication = dateApplication;
        this.addInfo = addInfo;
        this.serviceDopDto = serviceDopDto;
    }
}
