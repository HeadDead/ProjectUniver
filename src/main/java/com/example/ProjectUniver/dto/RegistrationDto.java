package com.example.ProjectUniver.dto;

import com.example.ProjectUniver.entity.ERole;
import lombok.Data;

@Data
public class RegistrationDto {

    private String id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phoneNumber;
    private String username;
    private String password;
    private String email;
    private String role = ERole.ROLE_USER.name();
}
