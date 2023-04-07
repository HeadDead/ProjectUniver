package com.example.psutipower.requests;

public class ServiceDopDto {
    String name;
    String code;
    String price;
    String duration;
    String addInfo;

    public ServiceDopDto(String name, String code, String price, String duration, String addInfo) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.duration = duration;
        this.addInfo = addInfo;
    }
}
