package com.example.ProjectUniver.service;


import com.example.ProjectUniver.dto.*;
import com.example.ProjectUniver.entity.User;

import javax.mail.MessagingException;

public interface UserService {
    JwtResponseDto login(LoginDto loginDto);
    MessageResponse registration(RegistrationDto registrationDto) throws MessagingException;
    MessageResponse registration(RegistrationOrganizationDto registrationOrganizationDto);
    User findUserByLogin(String login);

    User update(UpdateDto updateDto, User currentActor);

}
