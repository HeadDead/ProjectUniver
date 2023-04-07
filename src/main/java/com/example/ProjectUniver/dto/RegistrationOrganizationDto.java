package com.example.ProjectUniver.dto;

import com.example.ProjectUniver.entity.ERole;
import lombok.Data;

@Data
public class RegistrationOrganizationDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String phoneNumber;
    private String username;
    private String password;
    private String email;
    private String role = ERole.ROLE_ORGANIZATION.name();
    private String organizationFullName;
    private String organizationPassword;
    private String organizationShortName;
    private String inn;
    private String kpp;
    private String ogrn;
    private AddressDto addressDto;
    private Boolean approve;
}
